package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Service.RoomService;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService=new RoomService();
    @GetMapping
    public List<RoomEntity> getAllRooms() {
        return roomService.getAllRooms();
    }

    record NewRoomResponse(RoomEntity room){}
    record NewRoomRequset(int max_num_of_chairs, boolean status, HashSet<Integer> categoriesId){}
    @PostMapping
    public NewRoomResponse addRoom(@RequestBody   NewRoomRequset requset)  {
        RoomEntity room=new RoomEntity();
        room.setStatus(requset.status());
        room.setMax_num_of_chairs(requset.max_num_of_chairs());
        room.setCategories(requset.categoriesId());
        room.setTime_0f_reservation(null);
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        room.setId(rooms.size()+1);
        rooms.add(room);
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
        return new NewRoomResponse(room);
    }

}

