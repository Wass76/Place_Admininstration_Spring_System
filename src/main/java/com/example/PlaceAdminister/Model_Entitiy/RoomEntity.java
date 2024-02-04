package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class RoomEntity {
    @JsonProperty("id")
    @Id
    private Long id;
    @JsonProperty("max_num_of_chairs")
    private Integer max_num_of_chairs;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("time_0f_reservation")
    private int placeId;

    public RoomEntity(int max_num_of_chairs, Integer status, Date time_0f_reservation,int placeId) {
        this.max_num_of_chairs = max_num_of_chairs;
        this.status = status;
        this.placeId=placeId;
    }

}
