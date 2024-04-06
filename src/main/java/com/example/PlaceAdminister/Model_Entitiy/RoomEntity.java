package com.example.PlaceAdminister.Model_Entitiy;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import com.example.PlaceAdminister.Service.PlaceService;
import com.example.PlaceAdminister.Service.RoomService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class RoomEntity {

    @Id
    @SequenceGenerator(name = "room_id",
            sequenceName = "room_id" ,
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_id"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "max_num_of_chairs")
    private Integer max_num_of_chairs;
    @Column(name = "status")
    private Integer status;

//    @Lob
//    private byte[] image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "place_id",nullable = false )
    private PlaceEntity place;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_category_id",nullable = false )
    private RoomCategoryEntity roomCategory;


    public RoomEntity(RoomDTO roomDTO) throws IOException {
        max_num_of_chairs = roomDTO.getMax_num_of_chairs();
        status = roomDTO.getStatus();
//        image = roomDTO.getFile().getBytes();
//        place.setId(roomDTO.getPlace_id());
    }

//    public RoomEntity(int max_num_of_chairs, Integer status, Date time_0f_reservation,int placeId) {
//        this.max_num_of_chairs = max_num_of_chairs;
//        this.status = status;
//        this.placeId=placeId;
//    }

}
