package Service;

import Model_Entitiy.RoomEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private List<RoomEntity> rooms; // Assuming you load/save this list from/to JSON

    public List<RoomEntity> getAllRooms() {
        return rooms;
    }

    public void addRoom(RoomEntity room) {
        rooms.add(room);
        // Save the updated list to JSON
    }
    record NewRoomRequset(
            String name,
            String email,
            Integer age
    ){

    }
    // Other methods as needed
}

