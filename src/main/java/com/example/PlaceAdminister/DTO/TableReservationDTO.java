package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Model_Entitiy.TableReservation;
import com.example.PlaceAdminister.Request.TableReservationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TableReservationDTO {

    private Long place_id;
    private Long table_id;
    private Integer number_of_seats;
    private LocalDateTime time;
    private Integer period_of_reservations;

    public TableReservationDTO(TableReservationRequest request){
        table_id = request.getTable_id();
        number_of_seats = request.getNumber_of_seats();
        period_of_reservations = request.getPeriod_of_reservations();
    }
}
