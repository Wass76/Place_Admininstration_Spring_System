package com.example.PlaceAdminister.Request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PlaceRequest {
    private String name;
    private String locations;
//    private MultipartFile file;
}
