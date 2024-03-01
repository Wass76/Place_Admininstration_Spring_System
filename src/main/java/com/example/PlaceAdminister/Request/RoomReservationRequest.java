package com.example.PlaceAdminister.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RoomReservationRequest {

    private Long place_id;
    private Long room_id;
    private Integer number_of_seats;
    private String time;
    private Integer period_of_reservations;
}
