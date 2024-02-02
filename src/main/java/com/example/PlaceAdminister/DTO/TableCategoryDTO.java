package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Request.TableCategoryRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TableCategoryDTO {

    private Long id;
    private String Shape;
    private Integer num_of_seats;

    public TableCategoryDTO(TableCategoryRequest request){
        this.setShape( request.getShape());
        this.setNum_of_seats(request.getNum_of_seats());
    }

}
