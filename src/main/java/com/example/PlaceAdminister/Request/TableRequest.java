package com.example.PlaceAdminister.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@AllArgsConstructor
@Getter
@Setter
public class TableRequest {
    Long id;
    Integer status;
    Time time_of_reservation;
    Integer room_id;
}
