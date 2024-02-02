package com.example.PlaceAdminister.Controller;


import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.Request.MakeReservationRequest;
import com.example.PlaceAdminister.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("editReservation/{id}")
    public ReservationDTO edit(@PathVariable("id") Long id ,@RequestBody MakeReservationRequest request){
        ReservationDTO reservationDTO  = new ReservationDTO(request);
        return reservationService.update(id,reservationDTO);
    }


}
