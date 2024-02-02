package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Repository.RoomCategoryRepository;
import com.example.PlaceAdminister.Request.RoomRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private int max_num_of_chairs;
    private Set<Long> categoriesId = new HashSet<>();
    private Set<Long> tablesIds = new HashSet<>();
    private int status;
    private Date time_of_reservation;

    public RoomDTO(RoomRequest newRoomRequest) {
        this.status = newRoomRequest.getStatus();
        this.time_of_reservation = newRoomRequest.getTime_of_reservation();
        this.categoriesId = newRoomRequest.getCategoriesId();
        this.tablesIds = newRoomRequest.getTablesIds();
    }

}
