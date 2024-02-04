package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.Request.RoomCategoryRequest;
import com.example.PlaceAdminister.Service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {
    @Autowired
    private RoomCategoryService roomCategoryService;

    @GetMapping("allplaces")
    public List<RoomCategoryDTO> getAllRoomCategories() {
        return roomCategoryService.getAllRoomCategories();
    }

    @PostMapping("newplace")
    public RoomCategoryDTO addRoomCategory(@RequestBody RoomCategoryRequest roomCategoryRequest) {
        RoomCategoryDTO roomCategoryDTO = new RoomCategoryDTO(roomCategoryRequest);
        return roomCategoryService.store(roomCategoryDTO);
    }
}
