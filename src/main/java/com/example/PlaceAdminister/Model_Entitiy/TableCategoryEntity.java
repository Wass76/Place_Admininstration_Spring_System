package com.example.PlaceAdminister.Model_Entitiy;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TableCategoryEntity {

    @Id
    private Long id;

    @JsonProperty("shape")
    private String Shape;
    @JsonProperty("num_of_seats")
    private Integer num_of_seats = 4;

//    public TableCategoryEntity(){
//        TableCategoryEntity tableCategoryEntity1 = new TableCategoryEntity((long) 1,"Square" ,5);
//        TableCategoryEntity tableCategoryEntity2 = new TableCategoryEntity((long) 2,"Cycle" ,4);
//        TableCategoryEntity tableCategoryEntity3 = new TableCategoryEntity((long) 3,"Rectangle" ,6);
//    }


   }
