package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Request.TableRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TableDTO {
//    Long id;
    Integer status;
    Time time_of_reservation;
    Integer room_id;


    public TableDTO(TableRequest tableRequest){
        this.status = tableRequest.getStatus();
        this.time_of_reservation = tableRequest.getTime_of_reservation();
        this.room_id = tableRequest.getRoom_id();

//        info.add()
    }
}
