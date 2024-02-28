package com.example.PlaceAdminister.Model_Entitiy;


import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class TableCategoryEntity {

    @Id
    @SequenceGenerator(
            name = "table_category_id"
            ,sequenceName = "table_category_id"
            ,allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "table_category_id"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "shape")
    private String Shape;
    @Column(name = "num_of_seats")
    private Integer num_of_seats = 4;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @OneToMany
    @JoinColumn(name = "table_id")
    private Set<TableEntity> tables;


    public TableCategoryEntity(TableCategoryDTO tableCategoryDTO){
        Shape = tableCategoryDTO.getShape();
        num_of_seats = tableCategoryDTO.getNum_of_seats();
        place = tableCategoryDTO.getPlace();
    }

//    public TableCategoryEntity(){
//        TableCategoryEntity tableCategoryEntity1 = new TableCategoryEntity((long) 1,"Square" ,5);
//        TableCategoryEntity tableCategoryEntity2 = new TableCategoryEntity((long) 2,"Cycle" ,4);
//        TableCategoryEntity tableCategoryEntity3 = new TableCategoryEntity((long) 3,"Rectangle" ,6);
//    }


   }
