package Controller;

import Model_Entitiy.RoomEntity;
import Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void addRoom(@RequestBody RoomEntity room) {
        roomService.addRoom(room);
    }

    // Other endpoints as needed
}

