package Controller;

import Model_Entitiy.RoomEntity;
import Service.RoomService;
import com.example.PlaceAdminister.JsonDealer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private RoomService roomService;
    record RoomResponse(
    ){}
    public RoomResponse greet (){
        RoomResponse respose=new RoomResponse(

        );
        return respose;
    }
    @GetMapping
    public RoomResponse getAllRooms() {
        return new RoomResponse();
    }

    @PostMapping
    public void addRoom(@RequestBody  NewRoomRequset requset)  {
        System.out.println("done");
        RoomEntity room=new RoomEntity();
        room.setStatus(requset.status());
        room.setMax_num_of_chairs(requset.max_num_of_chairs());
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/Rooms.json");
        rooms.add(room);
        roomService.writeToJsonFile(rooms,"src/main/resources/Rooms.json");
    }
    record NewRoomRequset(
            int max_num_of_chairs,
            String status
    ){

    }
}

