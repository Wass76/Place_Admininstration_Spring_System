package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.RoomReservationDTO;
import com.example.PlaceAdminister.DTO.TableReservationDTO;
import com.example.PlaceAdminister.Exception.ApiRequestException;
import com.example.PlaceAdminister.Model_Entitiy.RoomReservation;
import com.example.PlaceAdminister.Model_Entitiy.TableReservation;
import com.example.PlaceAdminister.Request.RoomReservationRequest;
import com.example.PlaceAdminister.Request.TableReservationRequest;
import com.example.PlaceAdminister.Service.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/table-reservations")
public class TableReservationController {

    @Autowired
    TableReservationService tableReservationService;

    @GetMapping("{place_id}/get-all")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity getAllForPlace(@PathVariable("place_id") Long place_id)
    {
        if(place_id == null || place_id<=0){
            throw new ApiRequestException("Invalid Id");
//            return ResponseEntity.badRequest().body("Invalid Id");
        }
        List<TableReservation> tableReservationList = tableReservationService.getAllByPlaceId(place_id);
        if(tableReservationList.isEmpty()){
            return ResponseEntity.status(200).body("There is no reservation yet");
        }
        return ResponseEntity.ok(tableReservationList);
    }

    @GetMapping("{place_id}/get-all-by-time")
//    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity getAllByPlaceIdAtTime(@PathVariable("place_id") Long place_id , String time)
    {
        LocalDateTime time1 = LocalDateTime.now();
        System.out.printf("time is:" + time1);

//        if(time != null){
        String timeStr = time;
        LocalDateTime localDateTime = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
//        }

        if(place_id == null || place_id<=0){
            throw new ApiRequestException("Invalid Id");
//            return ResponseEntity.badRequest().body("Invalid Id");
        }
        List<TableReservation> tableReservationList = tableReservationService.getAllByPlaceIdAtTime(place_id ,localDateTime);
        if(tableReservationList.isEmpty()){
            return ResponseEntity.status(200).body("There is no reservation yet");
        }
        return ResponseEntity.ok(tableReservationList);
    }

    @PutMapping("reservation/takeMine/{id}")
//    @PreAuthorize("hasRole('USER' , 'MANA')")
    public ResponseEntity takeMyReservation(@PathVariable("id") Long id){
        try {
            if(id == null || id<=0){
                throw new ApiRequestException("Invalid Id");
//                return ResponseEntity.badRequest().body("Invalid Id");
            }
            return ResponseEntity.ok(tableReservationService.takeReservation(id));
        }catch (Exception e){
            throw new ApiRequestException("An error occurred while creating reservation, maybe room_id or place_id are not correct");
//            return ResponseEntity.internalServerError().body("An error occurred while creating reservation, maybe room_id or place_id are not correct");
        }

    }

    @PostMapping("{place_id}/reserve-table")
    public ResponseEntity ReserveTable(@RequestBody TableReservationRequest request, @PathVariable("place_id") Long place_id)
    {
        if(place_id == null || place_id<=0){
            throw new ApiRequestException("Invalid Id");
//            return ResponseEntity.badRequest().body("Invalid Id");
        }

        if(request.getTable_id() == null || request.getTime() == null || request.getPeriod_of_reservations() == null ){
            throw new ApiRequestException("Validate your data please");
//            return ResponseEntity.badRequest().body("validate your data please");
        }

        try {
            String timeStr = request.getTime();
            LocalDateTime time = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            TableReservationDTO reservationDTO = new TableReservationDTO(request);
            reservationDTO.setTime(time);
            reservationDTO.setPlace_id(place_id);

            TableReservation reservation = tableReservationService.reserve(reservationDTO);
            if(reservation.equals(null)){
                throw new ApiRequestException("Failed to create reservation");
//                return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Failed to create reservation");
            }
            return ResponseEntity.ok(reservation);
        }
        catch (Exception e){
            throw new ApiRequestException("An error occurred while creating reservation, maybe table_id or place_id are not correct");
//            return ResponseEntity.internalServerError().body("An error occurred while creating reservation, maybe table_id or place_id are not correct");
        }
    }

    @PutMapping("{place_id}/update-reservation/{id}")
    public ResponseEntity UpdateReservation(@RequestBody TableReservationRequest request ,@PathVariable("place_id") Long place_id ,@PathVariable("id") Long id){
        {
            if(place_id == null || place_id<=0 || id == null || id<=0 ){
                throw new ApiRequestException("Invalid Id");
//                return ResponseEntity.badRequest().body("Invalid Id");
            }
            if(request.getTable_id() == null
                    || request.getTime() == null
                    || request.getPeriod_of_reservations() == null ){
                throw new ApiRequestException("Validate your data please");
//                return ResponseEntity.badRequest().body("validate your data please");
            }

            try {
                String timeStr = request.getTime();
                LocalDateTime time = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                TableReservationDTO reservationDTO = new TableReservationDTO(request);
                reservationDTO.setTime(time);
                reservationDTO.setPlace_id(place_id);                TableReservation reservation = tableReservationService.updateReservation(reservationDTO,id);
                if(reservation.equals(null)){
                    throw new ApiRequestException("Failed to create reservation");
//                    return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Failed to create reservation");
                }
                return ResponseEntity.ok(reservation);
            }
            catch (Exception e){
                throw new ApiRequestException("An error occured while creating reservation, maybe table_id or place_id are not correct");
//                return ResponseEntity.internalServerError().body("An error occurred while creating reservation, maybe table_id or place_id are not correct");
            }
        }
    }

    @DeleteMapping("{place_id}/cancel-reservation/{id}")
    public ResponseEntity CancelReservation(@PathVariable("id") Long id , @PathVariable("place_id") Long place_id){
        if(place_id == null || place_id<=0 || id == null || id<=0 ){
            throw new ApiRequestException("Invalid Id");
//            return ResponseEntity.badRequest().body("Invalid Id");
        }
        tableReservationService.cancelReservation(id);
        return ResponseEntity.ok("deleted done successfully");
    }
}
