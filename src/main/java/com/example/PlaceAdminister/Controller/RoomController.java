package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Service.RoomService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    RoomController(){
        roomService=new RoomService();
    }


//    record RoomResponse(
//            int max_num_of_chairs,
//            Set<Long> categoriesId,
//            String status
//    ){}
//
//    @GetMapping
//    public RoomResponse getAllRooms() {
//        return new RoomResponse("hi");
//    }

    record NewRoomResponse(RoomEntity room){}
    record NewRoomRequset(int max_num_of_chairs, boolean status){}
    @PostMapping
    public NewRoomResponse addRoom(@RequestBody   NewRoomRequset requset)  {
        RoomEntity room=new RoomEntity();
        room.setStatus(requset.status());
        room.setMax_num_of_chairs(requset.max_num_of_chairs());
        room.setCategories(new HashSet<>());
        room.setTime_0f_reservation(null);
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        rooms.add(room);
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
        return new NewRoomResponse(room);
    }

}

