package com.example.PlaceAdminister.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class RoomCategoryRequest {
    private String type;
    private Long place_id;
//    private Integer num_of_seats;
//    private MultipartFile file;
}
