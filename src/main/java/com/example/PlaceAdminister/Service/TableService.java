package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableEntity tables;
    @Autowired
    private TableRepository tableRepository;
    private String filepath = "src/main/resources/Tables.json";

    public List<TableDTO> getAllTables(){

        List<TableDTO>  tableDTOList = tableRepository.readFromJsonTable(filepath);

        return tableDTOList;
    }

    public TableDTO store(TableDTO tableDTO){
           return tableRepository.writeToJsonTable(tableDTO ,this.filepath);
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


    public ReservationDTO reserveTable( ReservationDTO reservationDTO){
        String filePath = "src/main/resources/Reservations.json";
              ReservationDTO r=  tableRepository.reserveTable(reservationDTO,filePath);
            return r;
    }

    public void cancelTableReservation(Long id ) {
//        TableDTO tableDTO = tableRepository.searchDataById(id, filepath);
//        if (tableDTO != null) {
////            if(tableDTO.getStatus() == 2){
//            tableDTO.setStatus(1);
////            tableDTO.setTime_of_reservation(null);
//
//            tableRepository.UpdateById(id, tableDTO, filepath);
//            return tableDTO;
//        } else {
//            return null;
//        }

        String filepath = "src/main/resources/Reservations.json";
        tableRepository.deleteById(id,filepath);
    }

//    public Boolean checkAvailableSeats(Long id){
//        TableDTO tableDTO = tableRepository.searchDataById(id,filepath);
//        if(tableDTO != null){
//            if(tableDTO.getAvailable_seats().size()>0 ) // findAny().equals(true)
//                 return tableDTO.getAvailable_seats().stream().filter(i->i.equals(true)).findFirst().orElse(null);
//        }
//            return false;
//    }

    public void delete(Long id){
        tableRepository.deleteById(id,this.filepath);
    }
}
