package Model_Entitiy;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class RoomCategoryEntity {

    @SequenceGenerator(
            name = "Room_Category_id_sequence",
            sequenceName = "Room_Category_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Room_Category_id_sequence"
    )
    @Id
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
}
