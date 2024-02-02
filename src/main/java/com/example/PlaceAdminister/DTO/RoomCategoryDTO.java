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
public class RoomCategoryDTO extends AbstractDTO{
    private Long id;
    private String type;

    public RoomCategoryDTO(RoomCategoryRequest request) {
        this.type=request.getType();
    }
}
