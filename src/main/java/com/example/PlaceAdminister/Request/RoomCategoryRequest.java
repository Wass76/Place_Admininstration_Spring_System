package com.example.PlaceAdminister.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@Getter
@Setter
public class RoomCategoryRequest {
    private String type;
    private Set<Long> roomIds = new HashSet<>();
}
