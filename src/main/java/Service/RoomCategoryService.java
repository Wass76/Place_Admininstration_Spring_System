package Service;

import Model_Entitiy.RoomCategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomCategoryService {
    private List<RoomCategoryEntity> roomCategories;

    public List<RoomCategoryEntity> getAllRoomCategories() {
        return roomCategories;
    }

    public void addRoomCategory(RoomCategoryEntity roomCategoryEntity) {
        roomCategories.add(roomCategoryEntity);
        // Save the updated list to JSON
    }
}
