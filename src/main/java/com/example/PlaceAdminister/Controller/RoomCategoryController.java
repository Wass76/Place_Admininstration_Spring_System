package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.Request.RoomCategoryRequest;
import com.example.PlaceAdminister.Service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@RestController
@RequestMapping("/api/v1/roomCategories")
public class RoomCategoryController {
    @Autowired
    private RoomCategoryService roomCategoryService;

    @GetMapping("allRoomCategories")
    public ResponseEntity getAllRoomCategories() {
        List<RoomCategoryDTO> roomCategoryList = roomCategoryService.getAllRoomCategories();
        if(roomCategoryList.isEmpty()){
            return ResponseEntity.status(200).body("there is no room category yet");
        }
        return ResponseEntity.ok(roomCategoryList);
    }

    @PostMapping("newRoomCategory")
    public ResponseEntity addRoomCategory(@RequestBody RoomCategoryRequest roomCategoryRequest) {
        if(roomCategoryRequest.getType() == null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        RoomCategoryDTO roomCategoryDTO = new RoomCategoryDTO(roomCategoryRequest);
        RoomCategoryDTO newRoomCategory = roomCategoryService.store(roomCategoryDTO);
        if(newRoomCategory == null){
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
        }
        return ResponseEntity.ok(newRoomCategory);
    }

    @GetMapping("{id}")
    public ResponseEntity show(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        RoomCategoryDTO roomCategory = roomCategoryService.getRoomCategory(id);
        if(roomCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        return ResponseEntity.ok(roomCategory);
    }

    @PutMapping("update/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody RoomCategoryRequest request){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(request.getType() == null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        RoomCategoryDTO roomCategoryDTO = new RoomCategoryDTO(request);
        RoomCategoryDTO roomCategory = roomCategoryService.getRoomCategory(id);
        if(roomCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        return ResponseEntity.ok(roomCategoryService.update(id ,roomCategoryDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        RoomCategoryDTO roomCategory = roomCategoryService.getRoomCategory(id) ;
        if(roomCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        roomCategoryService.delete(id);
        return ResponseEntity.ok("Delete Done successfully");
    }

}

