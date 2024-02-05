package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Request.PlaceRequest;
import com.example.PlaceAdminister.Service.PlaceService;
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

    public PlaceDTO(String name, List<String> locations) {
        this.name = name;
        this.locations = locations;
    }
    public PlaceDTO(PlaceRequest placeRequest){
        this.name=placeRequest.getName();
        this.locations=placeRequest.getLocations();
    }
}
