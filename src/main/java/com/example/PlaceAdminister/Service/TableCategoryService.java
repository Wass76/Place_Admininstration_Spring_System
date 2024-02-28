package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableCategoryEntity;
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
//        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
        TableCategoryEntity tableCategory = new TableCategoryEntity(tableCategoryDTO);
        return tableCategoryRepository.save(tableCategory );
    }

    public TableCategoryEntity getTableCategory(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
        return tableCategoryRepository.getById(id);
    }


    public TableCategoryEntity update(Long id , TableCategoryDTO tableCategoryDTO)
    {
//        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
            TableCategoryEntity tableCategory = tableCategoryRepository.getById(id);
            if(tableCategory != null){
                tableCategory.setShape(tableCategoryDTO.getShape());
                tableCategory.setNum_of_seats(tableCategoryDTO.getNum_of_seats());
                tableCategory.setPlace(tableCategoryDTO.getPlace());
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
