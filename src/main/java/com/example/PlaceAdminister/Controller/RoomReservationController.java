package com.example.PlaceAdminister.Controller;


import com.example.PlaceAdminister.DTO.RoomReservationDTO;
import com.example.PlaceAdminister.Model_Entitiy.RoomReservation;
import com.example.PlaceAdminister.Request.RoomReservationRequest;
import com.example.PlaceAdminister.Service.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/room-reservations")
public class RoomReservationController {

    @Autowired
    private RoomReservationService roomReservationService;

    @GetMapping("{place_id}/get-all")
    public ResponseEntity getAllForPlace(@PathVariable("place_id") Long place_id)
    {
        if(place_id == null || place_id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        List<RoomReservation> roomReservationList = roomReservationService.getAllByPlaceId(place_id);
        if(roomReservationList.isEmpty()){
            return ResponseEntity.status(200).body("There is no reservation yet");
        }
        return ResponseEntity.ok(roomReservationList);
    }


    @GetMapping("{place_id}/get-all-by-time")
    public ResponseEntity getAllByPlaceIdAtTime(@PathVariable("place_id") Long place_id , String time)
    {
        LocalDateTime time1 = LocalDateTime.now();
        System.out.printf("time is:" + time1);

//        if(time != null){
            String timeStr = time;
         LocalDateTime localDateTime = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
//        }

        if(place_id == null || place_id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        List<RoomReservation> roomReservationList = roomReservationService.getAllByPlaceIdAtTime(place_id ,localDateTime);
        if(roomReservationList.isEmpty()){
            return ResponseEntity.status(200).body(roomReservationList);
        }
        return ResponseEntity.ok(roomReservationList);
    }

    @PostMapping("{place_id}/reserve-room")
    public ResponseEntity ReserveRoom(@RequestBody RoomReservationRequest request, @PathVariable("place_id") Long place_id)
    {
        if(place_id == null || place_id<=0){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        if(request.getRoom_id() == null ||request.getTime() == null || request.getPeriod_of_reservations() == null ){
                return ResponseEntity.badRequest().body("validate your data please");
        }
        try {
            String timeStr = request.getTime();
            LocalDateTime time = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            RoomReservationDTO reservationDTO = new RoomReservationDTO(request);
            reservationDTO.setTime(time);
            reservationDTO.setPlace_id(place_id);
            RoomReservation reservation = roomReservationService.reserve(reservationDTO);
            if(reservation.equals(null)){
                return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Failed to create reservation");
            }
            return ResponseEntity.ok(reservation);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("An error occurred while creating reservation, maybe room_id or place_id are not correct");
        }
    }

    @PutMapping("{place_id}/update-reservation/{id}")
    public ResponseEntity UpdateReservation(@RequestBody RoomReservationRequest request ,@PathVariable("place_id") Long place_id ,@PathVariable("id") Long id){
        {
            if(place_id == null || place_id<=0 || id == null || id<=0 ){
                return ResponseEntity.badRequest().body("Invalid Id");
            }
            if(request.getRoom_id() == null
                    || request.getTime() == null
                    || request.getPeriod_of_reservations() == null ){
                return ResponseEntity.badRequest().body("validate your data please");
            }
            try {
                String timeStr = request.getTime();
                LocalDateTime time = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                RoomReservationDTO reservationDTO = new RoomReservationDTO(request);
                reservationDTO.setTime(time);
                reservationDTO.setPlace_id(place_id);
                RoomReservation reservation = roomReservationService.updateReservation(reservationDTO,id);
                if(reservation.equals(null)){
                    return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("Failed to create reservation");
                }
                return ResponseEntity.ok(reservation);
            }
            catch (Exception e){
                return ResponseEntity.internalServerError().body("An error occurred while creating reservation, maybe room_id or place_id are not correct");
            }
        }
    }

    @DeleteMapping("{place_id}/cancel-reservation/{id}")
    public ResponseEntity CancelReservation(@PathVariable("id") Long id , @PathVariable("place_id") Long place_id){
        if(place_id == null || place_id<=0 || id == null || id<=0 ){
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        roomReservationService.cancelReservation(id);
        return ResponseEntity.ok("deleted done successfully");
    }
}
