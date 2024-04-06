package com.example.PlaceAdminister.Request;

import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class RoomRequest {
    private Integer max_num_of_chairs;
    private Long category_id;
    private Long place_id;
//    private MultipartFile file;
}
