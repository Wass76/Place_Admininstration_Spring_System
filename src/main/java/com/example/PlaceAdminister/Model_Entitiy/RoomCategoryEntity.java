package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RoomCategoryEntity {


    @Id
    private Long id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("roomIds")
    private Set<Long> roomIds = new HashSet<>();

    public RoomCategoryEntity(Long id , String type) {
        this.id = id;
        this.type=type;
    }


}
