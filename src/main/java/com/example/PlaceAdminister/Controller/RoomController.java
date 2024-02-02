package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Service.RoomService;
import org.apache.coyote.Response;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
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

    record NewRoomRequset(int max_num_of_chairs, boolean status, HashSet<Integer> categoriesId,HashSet<Integer>tablesId){}
    record NewRoomResponse(RoomEntity room){}
    @PostMapping
    public NewRoomResponse addRoom(@RequestBody   NewRoomRequset request)  {
        RoomEntity room=new RoomEntity(request.max_num_of_chairs(), request.categoriesId(),request.tablesId(),false,null);
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        room.setId(rooms.size()+1);
        rooms.add(room);
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
        return new NewRoomResponse(room);
    }

    record AddCategoryToRoomRequset(int id,List<Integer>categoriesId){}
    @PostMapping("/newCategory")
    public RoomEntity addCategoryToRoom(@RequestBody   AddCategoryToRoomRequset requset){
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        RoomEntity room=new RoomEntity();
        for (RoomEntity thisRoom: rooms) {
            if(thisRoom.getId()==requset.id()) {
                thisRoom.addCategory(requset.categoriesId());
                room=thisRoom;
            }
        }
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
        if(room.getId()==0){
            return null;
        }
        return room;
    }
    record AddTableToRoomRequset(int id,List<Integer>tablesId){}
    @PostMapping("/newTable")
    public RoomEntity addTableToRoomRequset(@RequestBody   AddTableToRoomRequset requset){
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        RoomEntity room=new RoomEntity();
        for (RoomEntity thisRoom: rooms) {
            if(thisRoom.getId()==requset.id()) {
                thisRoom.addTables(requset.tablesId());
                room=thisRoom;
            }
        }
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
        if(room.getId()==0){
            return null;
        }
        return room;
    }

    record MakeRoomReservedRequset(int id){}
    @PostMapping("/newTable")
    public RoomEntity makeRoomReservedRequset(@RequestBody   MakeRoomReservedRequset requset){
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        RoomEntity room=new RoomEntity();
        for (RoomEntity thisRoom: rooms) {
            if(thisRoom.getId()==requset.id()) {
                thisRoom.setStatus(true);
//                thisRoom.setTime_0f_reservation();
                room=thisRoom;
            }
        }
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
        if(room.getId()==0){
            return null;
        }
        return room;
    }


}

