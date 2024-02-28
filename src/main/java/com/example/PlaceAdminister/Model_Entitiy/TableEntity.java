package com.example.PlaceAdminister.Model_Entitiy;

import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Repository.TableCategoryRepository;
import com.example.PlaceAdminister.Service.TableCategoryService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.PrimitiveArray;
import org.springframework.stereotype.Component;

import javax.sound.midi.Sequence;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
//@Table(name = "table")
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class TableEntity extends TableCategoryEntity{

    @Id
    @SequenceGenerator(
            name = "table_id"
            ,sequenceName = "table_id"
            ,allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "table_id"
    )
    @Column(name = "id")
    private Long id;
//
    @Column(name = "available_seats")
    private Integer available_seats ;

    @Column(name = "status")
    private Integer status; //  1 = Available / 2 = Reserved / 3 =  Full
//    @ElementCollection
//    @JsonProperty("time_of_reservation")
//    private List<LocalDateTime> time_of_reservation;
//
//    @ElementCollection
//    @JsonProperty("period_of_reservation")
//    private List<Integer> period_of_reservation ;

    @ManyToOne
    @JoinColumn(name = "table_category_id")
    private TableCategoryEntity table_category;


    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    public TableEntity(TableDTO tableDTO){
        available_seats = tableDTO.getAvailable_seats();
        status = tableDTO.getStatus();
//        table_category = tableDTO.getCategory_id();
//        room = tableDTO.getRoom_id();
//        place = tableDTO.getCategory_id().getPlace();
    }

//    public TableEntity(Integer status, List<LocalDateTime> time_of_reservation, List<Integer> period_of_reservation, Long room_id , Long category_id)
//    {
//        this.status= status;
////        this.time_of_reservation= time_of_reservation;
//        this.room_id = room_id;
////        this.period_of_reservation =period_of_reservation;
//        this.category_id = category_id;


//        TableCategoryDTO tableCategoryDTO = tableCategoryService.getTableCategory(category_id);
////                tableCategoryRepository.searchDataById(category_id ,"src/main/resources/TableCategory.json");
////        if( tableCategoryDTO !=null){
//            Integer size = tableCategoryDTO.getNum_of_seats();
//            for(int i =0;i<size;i++){
//                available_seats.add(true);
//            }
//        }

//        int size = super.getNum_of_seats(); // Specify the size of the list
//
//        boolean[] initialValues = new boolean[size];
//        Arrays.fill(initialValues, false); // Initialize all values to false
//
//        List<Boolean> booleanList = Arrays.asList(initialValues);

//        this.available_seats=;
//    }
}
