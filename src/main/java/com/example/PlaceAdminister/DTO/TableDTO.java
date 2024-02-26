package com.example.PlaceAdminister.DTO;

import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Repository.TableCategoryRepository;
import com.example.PlaceAdminister.Request.TableRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class    TableDTO extends AbstractDTO{
    private Long id;
    private Integer status;

    private List<Boolean> available_seats = new ArrayList<>();

    private  Long category_id;

    private Long room_id;

    private String message;


    public TableDTO(TableRequest tableRequest) {
        this.status = tableRequest.getStatus();
        this.category_id = tableRequest.getCategory_id();
        this.room_id = tableRequest.getRoom_id();

        TableCategoryRepository tableCategoryRepository = new TableCategoryRepository();
        TableCategoryDTO tableCategoryDTO =tableCategoryRepository.searchDataById(category_id ,"src/main/resources/TableCategory.json");
        if( tableCategoryDTO !=null){
            Integer size = tableCategoryDTO.getNum_of_seats();
            for(int i =0;i<size;i++){
                available_seats.add(true);
            }
        }
    }

    public TableDTO(String message){
        this.message = message;
    }
}

