package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Request.MakeReservationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {

    private Long id;

    private Integer type; // 1= room , 2 = table , 3 = seat

    private Long room_id;

    private Long table_id;

    private Long seat_id;

    private LocalDateTime time;

    private Integer period_of_reservations;


    public ReservationDTO(MakeReservationRequest request){
        id = request.getId();
        type  = request.getType();
        room_id = request.getRoom_id();
        table_id = request.getTable_id();
        seat_id = request.getSeat_id();
        time = request.getTime();
        period_of_reservations =request.getPeriod_of_reservations();
    }

}
