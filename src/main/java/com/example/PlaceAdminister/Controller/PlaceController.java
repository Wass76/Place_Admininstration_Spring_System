package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.Request.PlaceRequest;
import com.example.PlaceAdminister.Request.RoomCategoryRequest;
import com.example.PlaceAdminister.Service.PlaceService;
import com.example.PlaceAdminister.Service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping("allplaces")
    public ResponseEntity getAllPlacess() {
        List<PlaceDTO> placeList  = placeService.getAllRoomCategories();
        if(placeList == null || placeList.isEmpty() ){
            return ResponseEntity.status(200).body("there is no Place yet");
        }
        return ResponseEntity.ok(placeList);
    }

    @PostMapping("newplace")
    public ResponseEntity addPlace(@RequestBody PlaceRequest placeRequest) {
        if(placeRequest.getLocations() == null || placeRequest.getLocations().isEmpty() || placeRequest.getName() == null){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        PlaceDTO placeDTO = new PlaceDTO(placeRequest);
        PlaceDTO place = placeService.store(placeDTO);
        if(place == null){
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
        }
        return ResponseEntity.ok(place);
    }
}
