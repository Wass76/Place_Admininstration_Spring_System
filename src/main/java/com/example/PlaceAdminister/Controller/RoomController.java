package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Request.RoomRequest;
import com.example.PlaceAdminister.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService=new RoomService();

    @GetMapping("/AllRooms")
    public ResponseEntity index(){
       List<RoomDTO> roomsList = roomService.getAllRooms() ;
       if(roomsList.isEmpty()){
           return ResponseEntity.status(200).body("there is no Rooms yet");
       }
        return ResponseEntity.ok(roomsList);

    }
    //checked

    @PostMapping("/newRoom")
    public ResponseEntity create(@RequestBody RoomRequest request)
    {
        if(request.getPlaceId() == null || request.getMax_num_of_chairs() == null || request.getCategory_id() == null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        RoomDTO roomDTO = new RoomDTO(request);
//        System.out.println(request.getMax_num_of_chairs());
        RoomDTO room = roomService.store(roomDTO);
        if(room == null){
                return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
        }
        return ResponseEntity.ok(room);
    }
    //checked

    @GetMapping("{id}")
    public ResponseEntity show(@PathVariable("id") Long id){
        if(id == null || id<=0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("d");
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        RoomDTO room = roomService.getItem(id);
        if(room == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        else {
            return  ResponseEntity.ok(room);
        }
         }

    @PutMapping("update/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody RoomRequest request){
        if(id == null || id<=0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("d");
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(request.getPlaceId() == null || request.getMax_num_of_chairs() == null || request.getCategory_id() == null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        RoomDTO roomDTO = new RoomDTO(request);
        RoomDTO room = roomService.getItem(id);
        if(room == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        return ResponseEntity.ok(roomService.update(id ,roomDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        RoomDTO room= roomService.getItem(id);
        if(room == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        roomService.delete(id);
        return ResponseEntity.ok("Delete Done successfully");
    }
}

