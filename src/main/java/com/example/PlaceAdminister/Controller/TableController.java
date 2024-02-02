package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.example.PlaceAdminister.Request.MakeReservationRequest;
import com.example.PlaceAdminister.Request.TableRequest;
import com.example.PlaceAdminister.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id ){
        tableService.delete(id);
    }

    record ReserveTableRequest(Time time){
    }

    record ReserveTableResponse(   Long id,
                                 Integer type, // 1= room , 2 = table , 3 = seat
                                 Long room_id,
                                Long table_id,
                                 Long num_of_seats,
                                 String time,
                                 Integer period_of_reservations){

    }

    @PostMapping("/reservationtable/{id}")
    public ReservationDTO reserveTable(@PathVariable("id") Long id , @RequestBody MakeReservationRequest request){
        if(request.getType() == 1){
            request.setRoom_id(id);
        } else if (request.getType() == 2) {
            request.setTable_id(id);
        }
        else{
            request.setTable_id(id);
        }
        ReservationDTO reservationDTO = new ReservationDTO(request);
        ReservationDTO  reservationDTO1=  tableService.reserveTable(reservationDTO);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd - hh:mm:ss");
//        String formattedDate = dateFormat.format(reservationDTO1.getTime());

//        Date date = request.getTime();
//        LocalDateTime dateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(reservationDTO1.getTime());
        System.out.println(formattedDate);


//        reservationDTO1.getTime().toLocalDate();
//        ReserveTableResponse reserveTableResponse = new ReserveTableResponse(reservationDTO1.getId(),reservationDTO1.getType(),reservationDTO1.getRoom_id(),reservationDTO1.getTable_id(),reservationDTO1.getNum_of_seats(),dateTime,reservationDTO1.getPeriod_of_reservations());

        return reservationDTO1;
    }
    @PostMapping("/cancelreserve/{id}")
    public void cancelReserve(@PathVariable("id") Long id){
        tableService.cancelTableReservation(id);
    }


    @PostMapping("/convertDateTime")
    public String convertDateTime(@RequestBody String inputDateTime) {
        // Define the input and output date formats
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");

//        try {
            // Parse the input date string into a LocalDateTime object
            System.out.println(inputDateTime);
            LocalDateTime dateTime = LocalDateTime.parse(inputDateTime,inputFormatter);


            // Format the LocalDateTime object into the desired output format
            String formattedDateTime = dateTime.format(outputFormatter);

            return "Formatted Date and Time: " + formattedDateTime;
//        } catch (Exception e) {
//            return "Invalid date format. Please provide the date in the format: yyyy/MM/dd - HH:mm:ss";
//        }
    }


}
