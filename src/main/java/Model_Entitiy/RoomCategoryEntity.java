package Model_Entitiy;

import com.example.PlaceAdminister.JsonDealer;
import jakarta.persistence.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class RoomCategoryEntity {

    @Id
    @SequenceGenerator(
            name = "Room_Category_id_sequence",
            sequenceName = "Room_Category_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Room_Category_id_sequence"
    )
    private Long id;
    private String name;
    private Set<Long> roomIds = new HashSet<>();
    private Set<Long> tableIds = new HashSet<>();

    public RoomCategoryEntity(Long id , String name) {
        this.id = id;
        this.name=name;
    }
    public RoomCategoryEntity(){}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(Set<Long> roomIds) {
        this.roomIds = roomIds;
    }

    public java.util.List<RoomEntity> getRooms(){
        List<RoomEntity> rooms=new ArrayList<>();
        //JsonDealer.readFromJson(new File("src/main/resources/Rooms.json"));
        for (Long id:roomIds) {


        }
        return rooms;
    }

}
