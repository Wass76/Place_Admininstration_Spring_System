package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Request.TableRequest;
import com.example.PlaceAdminister.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/tables")
public class TableController {

    @Autowired
    private TableService tableService;
    @Autowired
    private TableEntity tableEntity;

    @GetMapping("/reading")
    public List<TableDTO> test() throws IOException {
        String filepath = "src/main/resources/Tables.json";
        return tableService.readFromJsonFile(filepath);
    }

//    record TableRequest(
//            Long id,
//            Integer status,
//            Time time_of_reservation,
//            Integer room_id
//    ){}

    @PostMapping("writing")
    public List<TableDTO>  writeTest(@RequestBody TableRequest request){

        String filepath = "src/main/resources/Tables.json";
//        TableEntity t = new TableEntity();
//            t= (TableEntity) Arrays.asList(request);
        TableDTO t = new TableDTO(request);

        List<TableDTO> tableDTO = new ArrayList<>();
        tableDTO.add(t);
        System.out.println(tableDTO.toString());

        return tableService.writeDataToFile(filepath,tableDTO);
    }


}
