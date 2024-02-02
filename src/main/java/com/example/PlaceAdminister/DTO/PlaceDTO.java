package com.example.PlaceAdminister.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PlaceDTO extends AbstractDTO {
    private Long id;
    private String name;
    private List<String> locations;
    private List<String> rooms;

    public PlaceDTO(String name, List<String> locations, List<String> rooms) {
        this.name = name;
        this.locations = locations;
        this.rooms = rooms;
    }
}
