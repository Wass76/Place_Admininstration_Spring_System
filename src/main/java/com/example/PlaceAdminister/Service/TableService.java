package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Repository.RoomRepository;
import com.example.PlaceAdminister.Repository.TableCategoryRepository;
import com.example.PlaceAdminister.Repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalTime.now;

@Service
public class TableService {

    @Autowired
    private TableEntity tables;
    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TableCategoryRepository tableCategoryRepository;

    @Autowired
    private ReservationService reservationService;


    private final ResourceLoader resourceLoader;


    private String tableFilePath = "src/main/resources/Tables.json";
    private String roomFilePath = "src/main/resources/Rooms.json";

    private RoomService roomService;
    private String tableCategoryFilePath = "src/main/resources/TableCategory.json";

    public TableService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public List<TableDTO> getAllTables(){

        List<TableDTO>  tableDTOList = tableRepository.readFromJsonTable(tableFilePath);
        List<ReservationDTO> reservationDTOList = reservationService.getAllReservations();
//        LocalTime time = now();
//        if(reservationDTOList.stream().filter(i -> i.getTable_id()
//                == tableDTOList.stream().filter(j->j.getId().equals(i))))
        return tableDTOList;
    }

    public TableDTO store(TableDTO tableDTO){
    //        RoomDTO room = roomRepository.searchDataById(tableDTO.getRoom_id(),roomFilePath);
    //        System.out.println(tableDTO.getRoom_id());
    //        System.out.println(room);
    //        TableCategoryDTO tableCategory =  tableCategoryRepository.searchDataById(tableDTO.getCategory_id(),tableCategoryFilePath);
    //
    //        if(room.getMax_num_of_chairs() < tableCategory.getNum_of_seats()  )
    //            return new TableDTO("can't add new table to this room because there is no space for it");


//        if(roomRepository.readFromJsonFile().size() == 0){
//            return new TableDTO("you should add some rooms first");
//        }
        if(tableCategoryRepository.readFromJsonFile(tableCategoryFilePath).size() == 0){
            return new TableDTO("you should add table category first");
        }
        return tableRepository.writeToJsonTable(tableDTO ,this.tableFilePath);
    }

    public TableDTO getTable(Long id)
    {
        return tableRepository.searchDataById(id , this.tableFilePath);
    }

    public List<TableDTO> showTablesByRoomId(Long id)
    {
        return  tableRepository.searchByRoomId(id , this.tableFilePath);
    }

    public List<TableDTO> showTablesByCategoryId(Long id)
    {
        return  tableRepository.searchByCategoryId(id , this.tableFilePath);
    }

    public TableDTO update(Long id , TableDTO tableDTO){
        return tableRepository.UpdateById(id ,tableDTO,this.tableFilePath);
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
        tableRepository.deleteById(id,this.tableFilePath);
    }
}
