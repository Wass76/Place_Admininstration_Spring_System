package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableEntity tables;
    @Autowired
    private TableRepository tableRepository;
    private String filepath = "src/main/resources/Tables.json";

    public List<TableDTO> getAllTables(){

        return tableRepository.readFromJsonFile(filepath);
    }

    public TableDTO store(TableDTO tableDTO){
           return tableRepository.writeToJsonFile(tableDTO ,this.filepath);
    }

    public TableDTO show(Long id)
    {
        return tableRepository.searchDataById(id , this.filepath);
    }

    public List<TableDTO> showTablesByRoomId(Long id)
    {
        return  tableRepository.searchByRoomId(id , this.filepath);
    }

    public List<TableDTO> showTablesByCategoryId(Long id)
    {
        return  tableRepository.searchByCategoryId(id , this.filepath);
    }

    public TableDTO update(Long id , TableDTO tableDTO){
        return tableRepository.UpdateById(id ,tableDTO,this.filepath);
    }


    public TableDTO reserveTable(Long id , Time time){
        TableDTO tableDTO = tableRepository.searchDataById(id ,filepath);
        if(tableDTO != null){
            tableDTO.setStatus(2);
            tableDTO.setTime_of_reservation(time);
            tableRepository.UpdateById(id,tableDTO,filepath);
            return tableDTO;
        }
        else {
            return null;
        }
    }

    public TableDTO cancelTableReservation(Long id ) {
        TableDTO tableDTO = tableRepository.searchDataById(id, filepath);
        if (tableDTO != null) {
//            if(tableDTO.getStatus() == 2){
            tableDTO.setStatus(1);
            tableDTO.setTime_of_reservation(null);

            tableRepository.UpdateById(id, tableDTO, filepath);
            return tableDTO;
        } else {
            return null;
        }
    }

    public Boolean checkAvailableSeats(Long id){
        TableDTO tableDTO = tableRepository.searchDataById(id,filepath);
        if(tableDTO != null){
            if(tableDTO.getAvailable_seats().size()>0 ) // findAny().equals(true)
                 return tableDTO.getAvailable_seats().stream().filter(i->i.equals(true)).findFirst().orElse(null);
        }
            return false;
    }

    public void delete(Long id){

    }
}
