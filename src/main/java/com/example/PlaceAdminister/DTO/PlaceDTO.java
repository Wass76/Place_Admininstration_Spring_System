package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Request.PlaceRequest;
import com.example.PlaceAdminister.Service.PlaceService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PlaceDTO extends AbstractDTO {
    private Long id;
    private String name;
    private String locations;
    private MultipartFile file;

    public PlaceDTO(String name, String locations,MultipartFile file) {
        this.name = name;
        this.locations = locations;
        this.file = file;
    }
    public PlaceDTO(PlaceRequest placeRequest){
        this.name=placeRequest.getName();
        this.locations=placeRequest.getLocations();
//        this.file = placeRequest.getFile();
    }
}
