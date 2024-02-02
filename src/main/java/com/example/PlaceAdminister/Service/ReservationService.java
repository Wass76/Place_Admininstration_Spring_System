package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.AbstractDTO;
import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.Repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {

    @Autowired
    private AbstractRepository abstractRepository;

    private String filepath = "src/main/resources/Reservations.json";

    public List<ReservationDTO>  getAllReservations(){
        return abstractRepository.readFromJsonReservation(filepath);
    }

    public ReservationDTO update(Long id , ReservationDTO reservationDTO){
        return abstractRepository.UpdateById(id ,reservationDTO,filepath);
    }

}
