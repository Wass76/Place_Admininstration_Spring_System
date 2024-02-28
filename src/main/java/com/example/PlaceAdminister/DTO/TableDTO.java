package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Model_Entitiy.TableCategoryEntity;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Repository.TableCategoryRepository;
import com.example.PlaceAdminister.Request.TableRequest;
import com.example.PlaceAdminister.Service.TableCategoryService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TableDTO extends AbstractDTO{
    private Long id;
    private Integer status;

    private Integer available_seats ;

    private Long category_id;

    private Long room_id;

    private Long place_id;

    private String message;


    public TableDTO(TableRequest tableRequest) {
        this.status = tableRequest.getStatus();
        this.setCategory_id(tableRequest.getCategory_id());
        this.setRoom_id(tableRequest.getRoom_id());
        }

    public TableDTO(String message){
        this.message = message;
    }
}

