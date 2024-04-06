package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Request.RoomCategoryRequest;
import com.example.PlaceAdminister.Request.RoomRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RoomCategoryDTO extends AbstractDTO{
    private Long id;
    private String type;
//    private Integer num_of_seats;
    private Long place_id;
//    private MultipartFile file;


    public RoomCategoryDTO(RoomCategoryRequest request) {
        this.type=request.getType();
//        this.num_of_seats = request.getNum_of_seats();
//        this.place_id.setId( request.getPlace_id()) ;
        this.place_id = request.getPlace_id();
//        this.file = request.getFile();
    }
}
