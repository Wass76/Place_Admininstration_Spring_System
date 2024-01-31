package Controller;

import Model_Entitiy.RoomEntity;
import Service.RoomService;
import com.example.PlaceAdminister.JsonDealer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController


@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<RoomEntity> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public void addRoom(@RequestBody  NewRoomRequset requset) {
        RoomEntity room=new RoomEntity();
        room.setStatus(requset.status());
        room.setMax_num_of_chairs(requset.max_num_of_chairs());
        List<RoomEntity> rooms=roomService.readFromJsonFile("src/main/resources/application.properties");
        rooms.add(room);
        roomService.writeToJsonFile(rooms,"src/main/resources/application.properties");

    }
    record NewRoomRequset(
            int max_num_of_chairs,
//            Set<Long>categoriesId,
            String status
//            Time time_0f_reservation
    ){

    }
}

