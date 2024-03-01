package com.example.PlaceAdminister.DTO;


import com.example.PlaceAdminister.Request.RoomReservationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class RoomReservationDTO {

    private Long place_id;
    private Long room_id;
    private Integer number_of_seats;
    private LocalDateTime time;
    private Integer period_of_reservations;


   public RoomReservationDTO(RoomReservationRequest request){
        room_id = request.getRoom_id();
        number_of_seats = request.getNumber_of_seats();
        period_of_reservations = request.getPeriod_of_reservations();
    }

}
