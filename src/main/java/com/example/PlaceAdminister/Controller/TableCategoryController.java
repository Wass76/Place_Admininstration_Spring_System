package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Request.TableCategoryRequest;
import com.example.PlaceAdminister.Request.TableRequest;
import com.example.PlaceAdminister.Service.TableCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("api/v1/table-category")
public class TableCategoryController {

    @Autowired
    private TableCategoryService tableCategoryService;



    @GetMapping("/AllTables")
    public List<TableCategoryDTO> index(){
        return tableCategoryService.getAllTablesCategories();
    }

    @PostMapping("/newTable")
    public TableCategoryDTO create(@RequestBody TableCategoryRequest request)
    {
        TableCategoryDTO tableDTO = new TableCategoryDTO(request);
        return tableCategoryService.store(tableDTO);
    }

    @GetMapping("{id}")
    public TableCategoryDTO show(@PathVariable("id") Long id){
        return tableCategoryService.show(id);
    }

    @PutMapping("update/{id}")
    public TableCategoryDTO edit(@PathVariable("id") Long id ,@RequestBody TableCategoryRequest request){
        TableCategoryDTO tableDTO = new TableCategoryDTO(request);
        return tableCategoryService.update(id ,tableDTO);
    }

}
