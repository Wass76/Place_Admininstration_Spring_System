package com.example.PlaceAdminister.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class TableRequest {
    Long id;
    Integer status;
    LocalDateTime time_of_reservation;
    Long category_id;
    Long room_id;
}
