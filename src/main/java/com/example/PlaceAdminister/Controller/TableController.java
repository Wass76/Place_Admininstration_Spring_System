package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Request.TableRequest;
import com.example.PlaceAdminister.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("api/v1/tables")
public class TableController {

    @Autowired
    private TableService tableService;


    @GetMapping("/AllTables")
    public List<TableDTO> index(){
        return tableService.getAllTables();
    }

    @PostMapping("/newTable")
    public TableDTO create(@RequestBody TableRequest request)
    {
        TableDTO tableDTO = new TableDTO(request);
        return tableService.store(tableDTO);
    }

    @GetMapping("{id}")
    public TableDTO show(@PathVariable("id") Long id){
        return tableService.show(id);
    }
    @GetMapping("findByRoomId/{id}")
    public List<TableDTO> showByRoomId(@PathVariable("id") Long id){
        return tableService.showTablesByRoomId(id);
    }

    @GetMapping("findByCategoryId/{id}")
    public List<TableDTO> showByCategoryId(@PathVariable("id") Long id){
        return tableService.showTablesByCategoryId(id);
    }

    @PutMapping("update/{id}")
    public TableDTO edit(@PathVariable("id") Long id ,@RequestBody TableRequest request){
        TableDTO tableDTO = new TableDTO(request);
        return tableService.update(id ,tableDTO);
    }

    record ReserveTableRequest(Time time){
    }

    @PostMapping("/reservationtable/{id}")
    public TableDTO reserveTable(@PathVariable("id") Long id ,@RequestBody TableRequest request){
        return tableService.reserveTable(id,request.getTime_of_reservation());
    }
    @PostMapping("/cancelreserve/{id}")
    public TableDTO cancelReserve(@PathVariable("id") Long id){
        return tableService.cancelTableReservation(id);
    }


}
