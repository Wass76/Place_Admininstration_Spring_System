package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableCategoryEntity;
import com.example.PlaceAdminister.Request.TableCategoryRequest;
import com.example.PlaceAdminister.Service.TableCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("api/v1/table-category")
public class TableCategoryController {

    @Autowired
    private TableCategoryService tableCategoryService;

    @GetMapping("{place_id}/AllTables")
    public ResponseEntity index(@PathVariable("place_id") Long place_id){
        List<TableCategoryEntity> tableCategoryList = tableCategoryService.getAllTablesCategories();
        if(tableCategoryList.isEmpty()){
            return ResponseEntity.status(200).body(tableCategoryList);
        }
        return ResponseEntity.ok(tableCategoryList);
    }

    @PostMapping("/{place_id}/newTable")
    public ResponseEntity create(@RequestBody TableCategoryRequest request ,@PathVariable("place_id") Long place_id)
    {
        if(place_id == null ||  place_id <=0 ){
            return ResponseEntity.badRequest().body("validate your place_id please");
        }
        if(request.getShape() == null || request.getNum_of_seats() == null ){
            return ResponseEntity.badRequest().body("validate your data please");
        }
        TableCategoryDTO tableDTO = new TableCategoryDTO(request);
        tableDTO.setPlace(place_id);
        try {
            TableCategoryEntity newTableCategory = tableCategoryService.store(tableDTO);
            if(newTableCategory == null){
                return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
            }
            return ResponseEntity.ok(newTableCategory);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("An error occurred while creating the table category, maybe place_id is not correct");
        }
    }
    @GetMapping("{place_id}/view-table-category/{id}")
    public ResponseEntity show(@PathVariable("id") Long id ,@PathVariable("place_id") Long place_id){
        try{
            if(id == null || id<=0){
                return ResponseEntity.badRequest().body("Invalid Id");
            }
            if(place_id == null || place_id<=0){
                return ResponseEntity.badRequest().body("Invalid place_id");
            }
            TableCategoryEntity tableCategory = tableCategoryService.getTableCategory(id);
            if(tableCategory == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
            }
            return ResponseEntity.ok(tableCategory);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("An error occurred, maybe place_id or id are not correct");
        }

    }

    @PutMapping("{place_id}/update/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody TableCategoryRequest request,@PathVariable("place_id") Long place_id){
        try {
            if(id == null || id<=0){
                return ResponseEntity.badRequest().body("Invalid Id");
            }
            if(request.getShape() == null || request.getNum_of_seats() == null ){
                return ResponseEntity.badRequest().body("validate your data please");
            }
            TableCategoryDTO tableDTO = new TableCategoryDTO(request);
            tableDTO.setPlace(place_id);
            TableCategoryEntity tableCategory = tableCategoryService.getTableCategory(id);
            if(tableCategory == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
            }
            return ResponseEntity.ok(tableCategoryService.update(id ,tableDTO));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("An error occurred while updating the table category, maybe place_id or id are not correct");
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(id == null || id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        TableCategoryEntity tableCategory = tableCategoryService.getTableCategory(id);
        if(tableCategory == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        tableCategoryService.delete(id);
        return ResponseEntity.ok("Delete Done successfully");
    }

}
