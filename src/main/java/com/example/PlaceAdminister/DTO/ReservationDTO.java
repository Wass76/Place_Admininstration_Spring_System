package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Model_Entitiy.ReservationsEntity;
import com.example.PlaceAdminister.Request.MakeReservationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO extends AbstractDTO {

    private Long id;

    private Integer type; // 1= room , 2 = table , 3 = seat

    private Long room_id;

    private Long table_id;

    private Long num_of_seats;

    private Time time;

    private Integer period_of_reservations;


    public ReservationDTO(MakeReservationRequest request){
        id = request.getId();
        type  = request.getType();
        room_id = request.getRoom_id();
        table_id = request.getTable_id();
        num_of_seats = request.getNum_of_seats();
        time = request.getTime();
        period_of_reservations =request.getPeriod_of_reservations();
    }

    public ReservationDTO(ReservationsEntity reservationsEntity){
        id = reservationsEntity.getId();
        type  = reservationsEntity.getType();
        room_id = reservationsEntity.getRoom_id();
        table_id = reservationsEntity.getTable_id();
        num_of_seats = reservationsEntity.getNum_of_seats();
        time = reservationsEntity.getTime();
        period_of_reservations =reservationsEntity.getPeriod_of_reservations();
    }

}
