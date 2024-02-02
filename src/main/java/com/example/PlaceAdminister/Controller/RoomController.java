package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Service.RoomService;
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

    record NewRoomRequset(int max_num_of_chairs, boolean status, HashSet<Integer> categoriesId,HashSet<Integer>tablesId){}
    record NewRoomResponse(RoomEntity room){}
    @PostMapping
    public NewRoomResponse addRoom(@RequestBody  NewRoomRequset request)  {
        RoomEntity room=new RoomEntity(request.max_num_of_chairs(), request.categoriesId(),request.tablesId(),false,null);
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        room.setId(rooms.size()+1);
        rooms.add(room);
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
        return new NewRoomResponse(room);
    }

    record AddCategoryToRoomRequest(int id, List<Integer>categoriesId){}
    @PostMapping("/newCategory")
    public RoomEntity addCategoryToRoom(@RequestBody AddCategoryToRoomRequest request){
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        RoomEntity room=new RoomEntity();
        for (RoomEntity thisRoom: rooms) {
            if(thisRoom.getId()== request.id()) {
                thisRoom.addCategory(request.categoriesId());
                room=thisRoom;
            }
        }
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
        if(room.getId()==0){
            return null;
        }
        return room;
    }
    record AddTableToRoomRequest(int id, List<Integer>tablesId){}
    @PostMapping("/newTable")
    public RoomEntity addTableToRoomRequset(@RequestBody AddTableToRoomRequest request){
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        RoomEntity room=new RoomEntity();
        for (RoomEntity thisRoom: rooms) {
            if(thisRoom.getId()==request.id()) {
                thisRoom.addTables(request.tablesId());
                room=thisRoom;
            }
        }
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
        if(room.getId()==0){
            return null;
        }
        return room;
    }

//    record MakeRoomReservedRequest(int id){}
//    @PostMapping("/newTable")
//    public RoomEntity makeRoomReservedRequset(@RequestBody MakeRoomReservedRequest request){
//        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
//        RoomEntity room=new RoomEntity();
//        for (RoomEntity thisRoom: rooms) {
//            if(thisRoom.getId()==request.id()) {
//                thisRoom.setStatus(true);
////                thisRoom.setTime_0f_reservation();
//                room=thisRoom;
//            }
//        }
//        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
//        if(room.getId()==0){
//            return null;
//        }
//        return room;
//    }


}

