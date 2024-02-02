package com.example.PlaceAdminister.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TableRequest {
    Long id;
    Integer status;
    Long category_id;
    Long room_id;
}
