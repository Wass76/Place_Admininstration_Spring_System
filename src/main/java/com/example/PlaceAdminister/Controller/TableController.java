package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.DTO.TableCategoryDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Exception.ApiRequestException;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Model_Entitiy.TableCategoryEntity;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Request.TableRequest;
import com.example.PlaceAdminister.Service.RoomService;
import com.example.PlaceAdminister.Service.TableCategoryService;
import com.example.PlaceAdminister.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private TableCategoryService tableCategoryService;


    @GetMapping("/{place_id}/AllTables")
    public ResponseEntity index(@PathVariable("place_id") Long place_id){
        List<TableEntity> tablesList = tableService.getAllTables();
        if(tablesList.isEmpty()){
            return ResponseEntity.status(200).body(tablesList);
        }
        return ResponseEntity.ok(tablesList);
    }


    @PostMapping("/{place_id}/newTable")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity create(@RequestBody TableRequest request , @PathVariable("place_id") Long place_id)
    {
        if(request.getRoom_id() == null ||request.getCategory_id() == null ){
            throw new ApiRequestException("Validate your data please");
//            return ResponseEntity.badRequest().body("validate your data please");
        }
        TableDTO tableDTO = new TableDTO(request);
        try {
            tableDTO.setPlace_id(place_id);
            TableEntity newTable = tableService.store(tableDTO);

            if(newTable == null){
                throw new ApiRequestException("Failed to create table");
//                return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Failed to create table");
            }
            return ResponseEntity.ok(newTable);
        }catch (Exception e){
            throw new ApiRequestException("An error occurred while creating the room, maybe room_id or category_id not correct");
//            return ResponseEntity.internalServerError().body("An error occurred while creating the room, maybe room_id or category_id not correct");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity show(@PathVariable("id") Long id){
        try {
            if(id == null || id<=0){
                throw new ApiRequestException("Invalid Id");
//                return ResponseEntity.badRequest().body("Invalid id");
            }
            TableEntity table = tableService.getTable(id);
            if(table == null){
                throw new ApiRequestException("Can't find this item");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
            }
            return ResponseEntity.ok(table);
        }catch (Exception e){
            throw new ApiRequestException("An error occurred, maybe place_id or id are not correct");
//            return ResponseEntity.internalServerError().body("An error occurred, maybe place_id or id are not correct");
        }

    }
    @GetMapping("{place_id}/findByRoomId/{id}")
    public ResponseEntity showByRoomId(@PathVariable("id") Long id , @PathVariable("place_id") Long place_id){
        if(id == null || id<=0){
            throw new ApiRequestException("Invalid room id");
//            return ResponseEntity.badRequest().body("Invalid room id");
        }
        RoomEntity roomDTO = roomService.getItem(id);

        if(roomDTO == null){
            throw new ApiRequestException("Can't find this room");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this room");
        }

        List<TableEntity> tableRoomsList =tableService.showTablesByRoomId(id);
        if(tableRoomsList == null){
            throw new ApiRequestException("There is no tables in this room yet");
//            return ResponseEntity.status(200).body("there is no tables in this room yet");
        }
        return ResponseEntity.ok(tableRoomsList);
    }

    @GetMapping("{place_id}/findByCategoryId/{id}")
    public ResponseEntity showByCategoryId(@PathVariable("id") Long id, @PathVariable("place_id") Long place_id){
        if(id == null || id<=0){
            throw new ApiRequestException("Invalid room id");
//            return ResponseEntity.badRequest().body("Invalid room id");
        }
        TableCategoryEntity tableCategory = tableCategoryService.getTableCategory(id);
        if(tableCategory == null){
            throw new ApiRequestException("Can't find this table category");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this table category");
        }
        List<TableEntity> tableList = tableService.showTablesByCategoryId(id);
        if(tableList == null){
//            throw new ApiRequestException("There are no tables in this category yet")
            return ResponseEntity.status(200).body("There is no tables in this category yet");
        }
        return ResponseEntity.ok(tableList) ; //
    }

    @PutMapping("{place_id}/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody TableRequest request ,@PathVariable("place_id") Long place_id){
        try {
            if(id == null || id<=0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("d");
                throw new ApiRequestException("Invalid id");
//                return ResponseEntity.badRequest().body("Invalid Id");
            }
            if(request.getRoom_id() == null ||request.getCategory_id() == null ){
                throw new ApiRequestException("Validate your data please");
//                return ResponseEntity.badRequest().body("validate your data please");
            }
            TableDTO tableDTO = new TableDTO(request);
            tableDTO.setPlace_id(place_id);
            TableEntity table = tableService.getTable(id);
            if(table == null){
                throw new ApiRequestException("Can't find this item");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
            }
            return ResponseEntity.ok(tableService.update(id ,tableDTO));
        }catch (Exception e){
            throw new ApiRequestException("An error occurred while updating the table, maybe place_id or id or category_id are not correct");
//            return ResponseEntity.internalServerError().body("An error occurred while updating the table, maybe place_id or id or category_id are not correct");

        }
    }
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity delete(@PathVariable("id") Long id ){
        if(id == null || id<=0){
            throw new ApiRequestException("Invalid Id");
//            return ResponseEntity.badRequest().body("Invalid Id");
        }

        TableEntity table = tableService.getTable(id);
        if(table == null){
            throw new ApiRequestException("Can't find this item");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        tableService.delete(id);
        return ResponseEntity.ok("delete Done successfully");
    }
}
