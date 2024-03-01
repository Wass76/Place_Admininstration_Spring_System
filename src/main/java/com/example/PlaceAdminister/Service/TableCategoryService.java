package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Model_Entitiy.TableCategoryEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import com.example.PlaceAdminister.Repository.TableCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableCategoryService {

    @Autowired
    private TableCategoryEntity tableCategoryEntity;

    @Autowired
    private TableCategoryRepository tableCategoryRepository;
    @Autowired
    private PlaceRepository placeRepository;

    private final ResourceLoader resourceLoader;

    private String filepath = "src/main/resources/TableCategory.json";

    public TableCategoryService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }



    public List<TableCategoryEntity> getAllTablesCategories(){
//        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
        return tableCategoryRepository.findAll();
    }

    public TableCategoryEntity store(TableCategoryDTO tableCategoryDTO){
        TableCategoryEntity tableCategory = new TableCategoryEntity(tableCategoryDTO);

        if(placeRepository.existsById(tableCategoryDTO.getPlace())){
        PlaceEntity place = placeRepository.getById(tableCategoryDTO.getPlace());

        tableCategory.setPlace(place);
        tableCategory.setPlace(placeRepository.getById(tableCategoryDTO.getPlace()));
//        System.out.println( "id: "+tableCategory.getId());
//        System.out.println( "shape: "+tableCategory.getShape());
//        System.out.println( "Num_of_seats: "+tableCategory.getNum_of_seats());
//        System.out.println( "place: "+tableCategory.getPlace());
        return tableCategoryRepository.save(tableCategory );
        }
        return null;
    }

    public TableCategoryEntity getTableCategory(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
        try {
            return tableCategoryRepository.getById(id);
        }catch (Exception e){
        }
        return null;
    }


    public TableCategoryEntity update(Long id , TableCategoryDTO tableCategoryDTO)
    {
            TableCategoryEntity tableCategory = tableCategoryRepository.getById(id);
            if(tableCategory != null){
                PlaceEntity place = placeRepository.getById(tableCategoryDTO.getPlace());
                tableCategory.setShape(tableCategoryDTO.getShape());
                tableCategory.setNum_of_seats(tableCategoryDTO.getNum_of_seats());
                tableCategory.setPlace(place);
                tableCategoryRepository.save(tableCategory);
            }
            return tableCategory;
    }

    public void delete(Long id){
//        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
        TableCategoryEntity tableCategory = tableCategoryRepository.getReferenceById(id);
         tableCategoryRepository.deleteById(id);
    }




}
