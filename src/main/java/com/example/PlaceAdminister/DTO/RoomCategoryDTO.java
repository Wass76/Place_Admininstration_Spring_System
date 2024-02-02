package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Request.RoomCategoryRequest;
import com.example.PlaceAdminister.Request.RoomRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RoomCategoryDTO {
    private Long id;
    private String type;
    private Set<Long> roomIds = new HashSet<>();

    public RoomCategoryDTO(RoomCategoryRequest request) {
        this.type=request.getType();
        roomIds = request.getRoomIds();
    }
}
