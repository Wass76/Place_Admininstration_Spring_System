package com.example.PlaceAdminister.Model_Entitiy;

import com.example.PlaceAdminister.Request.MakeReservationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationsEntity {
    @Id
    private Long id;

    @JsonProperty
    private Integer type; // 1= room , 2 = table , 3 = seat

    @JsonProperty
    private Long room_id;

    @JsonProperty
    private Long table_id;
    @JsonProperty()
    private Long seat_id;
    @JsonProperty
    private LocalDateTime time;
    @JsonProperty
    private Integer period_of_reservations;

//    public ReservationsEntity(Long id,Integer type,Long room_id,Long table_id,Long seat_id,LocalDateTime time,Integer period_of_reservations){
//       this.id = id;
//        this.type  = type;
//        this.room_id = room_id;
//        this.table_id = table_id;
//        this. seat_id = seat_id;
//        this. time = time;
//        this. period_of_reservations = period_of_reservations;
//    }


}
