package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Request.RoomCategoryRequest;
import com.example.PlaceAdminister.Service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-categories")
public class RoomCategoryController {
    @Autowired
    private RoomCategoryService roomCategoryService;

    @GetMapping
    public List<RoomCategoryDTO> getAllRoomCategories() {
        return roomCategoryService.getAllRoomCategories();
    }

    @PostMapping
    public RoomCategoryDTO addRoomCategory(@RequestBody RoomCategoryRequest roomCategoryRequest) {
        RoomCategoryDTO roomCategoryDTO = new RoomCategoryDTO(roomCategoryRequest);
        return roomCategoryService.store(roomCategoryDTO);
    }

    // Other endpoints as needed
}

