package com.example.PlaceAdminister.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MakeReservationRequest {
    private Long id;

    private Integer type; // 1= room , 2 = table , 3 = seat

    private Long room_id;

    private Long table_id;

    private Long seat_id;

    private LocalDateTime time;

    private Integer period_of_reservations;
}
