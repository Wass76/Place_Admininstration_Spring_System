package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class PlaceEntity {
    @Id
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("locations")
    private List<String> locations;
    @JsonProperty("rooms")
    private List<String> rooms;

    public PlaceEntity(String name, List<String> locations, List<String> rooms) {
        this.name = name;
        this.locations = locations;
        this.rooms = rooms;
    }
}
