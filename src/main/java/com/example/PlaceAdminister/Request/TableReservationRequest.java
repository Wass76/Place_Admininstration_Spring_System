package com.example.PlaceAdminister.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TableReservationRequest {
    private Long place_id;
    private Long table_id;
    private Integer number_of_seats;
    private String time;
    private Integer period_of_reservations;
}
