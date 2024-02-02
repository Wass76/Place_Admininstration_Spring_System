package com.example.PlaceAdminister.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class RoomRequest {
    Integer status;
    Time time_of_reservation;
    private Set<Long> categoriesId;
    private Set<Long> tablesIds;


}
