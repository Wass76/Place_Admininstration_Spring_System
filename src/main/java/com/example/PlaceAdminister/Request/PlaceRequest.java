package com.example.PlaceAdminister.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PlaceRequest {
    private String name;
    private String locations;
    private String rooms;
}
