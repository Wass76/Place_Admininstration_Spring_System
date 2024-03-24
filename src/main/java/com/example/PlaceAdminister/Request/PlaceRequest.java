package com.example.PlaceAdminister.Request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PlaceRequest {
    private String name;
    private String locations;
    private String rooms;
}
