package com.example.PlaceAdminister.Controller;


import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.Request.MakeReservationRequest;
import com.example.PlaceAdminister.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("api/v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("getAll")
    public List<ReservationDTO>  getAllReservation(){
        return reservationService.getAllReservations();
    }


    @PostMapping("/reservation/{id}")
    public ReservationDTO reserve(@PathVariable("id") Long id , @RequestBody MakeReservationRequest request){

        ReservationDTO reservationDTO = new ReservationDTO(id ,request.getType() ,request.getRoom_id(),request.getTable_id(),request.getNum_of_seats(),request.getTime(),request.getPeriod_of_reservations());

        return reservationService.reserve(reservationDTO);
    }

    @PutMapping("editReservation/{id}")
    public ReservationDTO edit(@PathVariable("id") Long id ,@RequestBody MakeReservationRequest request){
        ReservationDTO reservationDTO  = new ReservationDTO(request);
        return reservationService.update(id,reservationDTO);
    }

    @DeleteMapping("cancelReservation/{id}")
    public String cancelReservation(@PathVariable("id") Long id){
        return reservationService.cancelReservation(id);
    }


}
