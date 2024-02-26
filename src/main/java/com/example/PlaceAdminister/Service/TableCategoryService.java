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



    public List<TableCategoryDTO> getAllTablesCategories(){
        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
        return tableCategoryRepository.readFromJsonFile(resource);
    }

    public TableCategoryDTO store(TableCategoryDTO tableCategoryDTO){
        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
        return tableCategoryRepository.writeToJsonFile(tableCategoryDTO ,resource);
    }

    public TableCategoryDTO getTableCategory(Long id)
    {
        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
        return tableCategoryRepository.searchDataById(id , resource);
    }


    public TableCategoryDTO update(Long id , TableCategoryDTO tableCategoryDTO)
    {
        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
        return tableCategoryRepository.UpdateById(id ,tableCategoryDTO,resource);
    }

    public void delete(Long id){
        Resource resource = resourceLoader.getResource("classpath:TableCategory.json");
         tableCategoryRepository.deleteById(id,resource);
    }




}
