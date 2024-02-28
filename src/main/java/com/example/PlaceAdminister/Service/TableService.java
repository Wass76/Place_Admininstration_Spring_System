package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Model_Entitiy.TableCategoryEntity;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
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

    @Autowired
    private PlaceRepository placeRepository;


    private final ResourceLoader resourceLoader;


    private String tableFilePath = "src/main/resources/Tables.json";
    private String roomFilePath = "src/main/resources/Rooms.json";

    private RoomService roomService;
    private String tableCategoryFilePath = "src/main/resources/TableCategory.json";

    public TableService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public List<TableEntity> getAllTables()
    {
        Resource resource = resourceLoader.getResource("classpath:Tables.json");

        List<TableEntity> tableDTOList = tableRepository.findAll();
        List<ReservationDTO> reservationDTOList = reservationService.getAllReservations();
//        LocalTime time = now();
//        if(reservationDTOList.stream().filter(i -> i.getTable_id()
//                == tableDTOList.stream().filter(j->j.getId().equals(i))))
        return tableDTOList;
    }

    public TableEntity store(TableDTO tableDTO)
    {
        TableEntity table = new TableEntity(tableDTO);
        TableCategoryEntity tableCategory = tableCategoryRepository.getById(tableDTO.getCategory_id());
        PlaceEntity place =placeRepository.getById(tableDTO.getPlace_id());
        RoomEntity room = roomRepository.getById(tableDTO.getRoom_id());
        Integer seats = tableCategory.getNum_of_seats();

        table.setTable_category(tableCategory);
        table.setRoom(room);
        table.setPlace(place);
        table.setAvailable_seats(seats);
        return tableRepository.save(table);
    }

    public TableEntity getTable(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        return tableRepository.getById(id);
    }

    public List<TableEntity> showTablesByRoomId(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        return  tableRepository.findAll().stream().filter(i->i.getRoom().getId().equals(id)).toList();
    }

    public List<TableEntity> showTablesByCategoryId(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        return  tableRepository.findAll().stream().filter(i->i.getTable_category().getId().equals(id)).toList();
    }

    public TableEntity update(Long id , TableDTO tableDTO)
    {
//        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        TableEntity table = tableRepository.getById(id);
        if(table != null){
            TableCategoryEntity tableCategory = tableCategoryRepository.getById(tableDTO.getCategory_id());
            PlaceEntity place =placeRepository.getById(tableDTO.getPlace_id());
            RoomEntity room = roomRepository.getById(tableDTO.getRoom_id());
            Integer seats = tableCategory.getNum_of_seats();

            table.setTable_category(tableCategory);
            table.setRoom(room);
            table.setPlace(place);
            table.setAvailable_seats(seats);
            tableRepository.save(table);
        }
        return table;
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
//        Resource resource = resourceLoader.getResource("classpath:Tables.json");
        TableEntity table = tableRepository.getReferenceById(id);
        if(table!= null){
            tableRepository.deleteById(id);
        }
//        tableRepository.deleteById(id,resource);
    }
}
