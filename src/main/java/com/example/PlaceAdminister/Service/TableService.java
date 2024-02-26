package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Repository.RoomRepository;
import com.example.PlaceAdminister.Repository.TableCategoryRepository;
import com.example.PlaceAdminister.Repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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


    public List<TableDTO> getAllTables()
    {
        Resource resource = resourceLoader.getResource("classpath:Tables.json");

        List<TableDTO>  tableDTOList = tableRepository.readFromJsonTable(resource);
        List<ReservationDTO> reservationDTOList = reservationService.getAllReservations();
//        LocalTime time = now();
//        if(reservationDTOList.stream().filter(i -> i.getTable_id()
//                == tableDTOList.stream().filter(j->j.getId().equals(i))))
        return tableDTOList;
    }

    public TableDTO store(TableDTO tableDTO)
    {
        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        Resource categoryResource = resourceLoader.getResource("classpath:TableCategory.json");
//        if(roomRepository.readFromJsonFile().size() == 0){
//            return new TableDTO("you should add some rooms first");
//        }
        if(tableCategoryRepository.readFromJsonFile(categoryResource).size() == 0){
            return new TableDTO("you should add table category first");
        }
        return tableRepository.writeToJsonTable(tableDTO ,resource);
    }

    public TableDTO getTable(Long id)
    {
        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        return tableRepository.searchDataById(id , resource);
    }

    public List<TableDTO> showTablesByRoomId(Long id)
    {
        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        return  tableRepository.searchByRoomId(id , resource);
    }

    public List<TableDTO> showTablesByCategoryId(Long id)
    {
        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        return  tableRepository.searchByCategoryId(id , resource);
    }

    public TableDTO update(Long id , TableDTO tableDTO)
    {
        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        return tableRepository.UpdateById(id ,tableDTO,resource);
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
        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        tableRepository.deleteById(id,resource);
    }
}
