package com.example.PlaceAdminister.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
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

    private Long num_of_seats;

    private Time time;

    private Integer period_of_reservations;
}
