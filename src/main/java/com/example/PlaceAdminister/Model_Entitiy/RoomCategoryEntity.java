package com.example.PlaceAdminister.Model_Entitiy;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class RoomCategoryEntity {
    @Id
    @SequenceGenerator(
            name = "room_category_id"
            ,sequenceName = "room_category_id"
            ,allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_category_id"
    )
    private Long id;
    @Column(name = "type")
    private String type;

//    @Column(name = "num_of_seats")
//    private Integer num_of_seats;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "place_id" ,nullable = false)
    private PlaceEntity place;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "room_id")
    private Set<RoomEntity> rooms;

    public RoomCategoryEntity(String type) {
        this.type=type;
    }


    public RoomCategoryEntity(RoomCategoryDTO roomCategoryDTO){
        type = roomCategoryDTO.getType();
//        num_of_seats  = roomCategoryDTO.getNum_of_seats();
    }


}
