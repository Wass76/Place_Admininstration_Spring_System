package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Exception.ApiRequestException;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import com.example.PlaceAdminister.Request.RoomRequest;
import com.example.PlaceAdminister.Service.PlaceService;
import com.example.PlaceAdminister.Service.RoomCategoryService;
import com.example.PlaceAdminister.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private RoomCategoryService roomCategoryService;


    @GetMapping("{place_id}/AllRooms")
    public ResponseEntity index(@PathVariable("place_id") Long place_id){
        if(place_id == null || place_id<=0){
            throw new ApiRequestException("Invalid Id");
//            return ResponseEntity.badRequest().body("Invalid Id");
        }
       List<RoomEntity> roomsList = roomService.getAllRooms(place_id);
       if(roomsList.isEmpty()){
           return ResponseEntity.status(200).body(roomsList);
       }
        return ResponseEntity.ok(roomsList);

    }
    //checked

    @PostMapping("/{place_id}/newRoom")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity create(
            @RequestBody RoomRequest request ,
//            @RequestParam Integer max_num_of_chairs,
//            @RequestParam Long category_id,
//            @RequestParam MultipartFile file,
            @PathVariable("place_id") Long place_id)
    {
//        RoomRequest request = RoomRequest.builder()
//                .max_num_of_chairs(max_num_of_chairs)
//                .category_id(category_id)
////                .file(file)
//                .place_id(place_id)
//                .build();
        if (request.getMax_num_of_chairs() == null || request.getCategory_id() == null) {
            throw new ApiRequestException("Validate your data please");
//            return ResponseEntity.badRequest().body("Validate your data please");
        }
        if (placeService.getById(place_id) == null) {
            throw new ApiRequestException("No such place");
//            return ResponseEntity.badRequest().body("No such place");
        }

        RoomDTO roomDTO = new RoomDTO(request);
        try {
            roomDTO.setPlace_id(place_id);
            RoomEntity room = roomService.store(roomDTO);
            if (room == null) {
//                System.out.println("Room creation failed");
//                System.out.println("Failed to create room for request: {}");
                throw new ApiRequestException("Failed to create room");
//                return ResponseEntity.internalServerError().body("Failed to create room");
            }
            return ResponseEntity.ok(room);
        } catch (Exception e) {
//            System.out.println("Error creating room: {}");
            throw new ApiRequestException("An error occurred while creating the room, maybe place_id or category_id not correct");
//            return ResponseEntity.internalServerError().body("An error occurred while creating the room, maybe place_id or category_id not correct");
        }
    }
    //checked

    @GetMapping("/{place_id}/show/{id}")
    public ResponseEntity show(@PathVariable("id") Long id ,@PathVariable("place_id") Long place_id){
        try {
            if(id == null || id<=0){
                throw new ApiRequestException("Invalid Id");
//                return ResponseEntity.badRequest().body("Invalid Id");
            }
            RoomEntity room = roomService.getItem(id);
            if(room == null){
                throw new ApiRequestException("can't find this item");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
            }
            if(room.getPlace().getId() != place_id){
                return ResponseEntity.status(403).body("you don't have permission to enter to this data");
            }
            else {
                return  ResponseEntity.ok(room);
            }
        }catch (Exception e){
            throw new ApiRequestException("An error occurred, maybe place_id or id are not correct");
//            return ResponseEntity.internalServerError().body("An error occurred, maybe place_id or id are not correct");

        }
    }

    @GetMapping("{place_id}/showByCategory/{category_id}")
    public ResponseEntity showByCategoryId(@PathVariable("category_id") Long id,@PathVariable("place_id") Long place_id){
        try {
            if(id == null || id<=0){
                throw new ApiRequestException("Invalid Id");
//                return ResponseEntity.badRequest().body("Invalid Id");
            }
            if(place_id == null || place_id<=0){
                throw new ApiRequestException("Invalid place_id");
//                return ResponseEntity.badRequest().body("Invalid place_id");
            }
            List<RoomEntity>  room = roomService.getByCategory(id);
            if(room == null){
                throw new ApiRequestException("Invalid place_id");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid place_id");
            }
//            if(room.getPlace().getId() !=place_id){
//                return ResponseEntity.status(403).body("you don't have permission to enter to this data");
//            }
            return ResponseEntity.ok(room);
        }catch (Exception e){
            throw new ApiRequestException("An error occurred , maybe place_id or category id are not correct");
//            return ResponseEntity.internalServerError().body("An error occurred , maybe place_id or category id are not correct");
        }
    }

    @PutMapping("/{place_id}/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody RoomRequest request ,@PathVariable("place_id") Long place_id){
        try {
            if(id == null || id<=0){
                throw new ApiRequestException("Invalid Id");
//                return ResponseEntity.badRequest().body("Invalid Id");
            }
            if(place_id == null || place_id<=0){
                throw new ApiRequestException("Invalid place_id");
//                return ResponseEntity.badRequest().body("Invalid place_id");
            }
            if( request.getMax_num_of_chairs() == null || request.getCategory_id() == null){
                throw new ApiRequestException("validate your data please");
//                return ResponseEntity.badRequest().body("validate your data please");
            }
            RoomDTO roomDTO = new RoomDTO(request);
            roomDTO.setPlace_id(place_id);
            RoomEntity room = roomService.getItem(id);
            if(room == null){
                throw new ApiRequestException("can't find this item");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
            }
            if(room.getPlace().getId() != place_id){
                return ResponseEntity.status(401).body("you don't have permission to enter to this data");
            }
            RoomEntity updatedRoom = roomService.update(id ,roomDTO);

            return ResponseEntity.ok(updatedRoom);
        }catch (Exception e){
        throw  new ApiRequestException("An error occurred while updating the room, maybe place_id or id or category_id are not correct");
//            return ResponseEntity.internalServerError().body("An error occurred while updating the room, maybe place_id or id or category_id are not correct");
        }

    }

    @DeleteMapping("/{place_id}/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity delete(@PathVariable("id") Long id ,@PathVariable("place_id") Long place_id){
        if(id == null || id<=0){
            throw new ApiRequestException("Invalid Id");
//            return ResponseEntity.badRequest().body("Invalid Id");
        }
        RoomEntity room= roomService.getItem(id);
        if(room == null){
            throw new ApiRequestException("can't find this item");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        if(room.getPlace().getId() != place_id){
            return ResponseEntity.status(401).body("you don't have permission to enter to this data");
        }

        roomService.delete(id);
        return ResponseEntity.ok("Delete Done successfully");
    }
}

