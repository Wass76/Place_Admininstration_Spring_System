package com.example.PlaceAdminister.Model_Entitiy;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PlaceEntity {

    @SequenceGenerator(name = "place_id",
            sequenceName = "place_id" ,
            allocationSize = 1)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "place_id"
    )
    private Long id;
    @Column(name =  "name")
    private String name;
    @Column(name = "location")
    private String location;

    @OneToMany
    @JoinColumn(name = "room_id")
    private Set<RoomEntity> rooms;

    @OneToMany
    @JoinColumn(name = "table_category_id")
    private Set<TableCategoryEntity> tableCategories;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<UserEntity> userEntities;


    public PlaceEntity(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public PlaceEntity(PlaceDTO placeDTO) {
        name = placeDTO.getName();
        location = placeDTO.getLocations();
    }
}
