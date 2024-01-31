package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@RestController
//@RequestMapping("/rooms")
//@RequestMapping("/rooms")

public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    @RequestMapping(value = "rooms" ,method = RequestMethod.GET)
    public List<RoomEntity> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public void addRoom(@RequestBody  NewRoomRequset requset) {
        RoomEntity room=new RoomEntity();
        room.setStatus(requset.status());
        room.setMax_num_of_chairs(requset.max_num_of_chairs());
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/application.yml");
        rooms.add(room);
        roomService.writeToJsonFile(rooms,"src/main/resources/application.yml");

    }
    record NewRoomRequset(
            int max_num_of_chairs,
//            Set<Long>categoriesId,
            String status
//            Time time_0f_reservation
    ){

    }
}

