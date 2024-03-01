package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.RoomReservationDTO;
import com.example.PlaceAdminister.DTO.TableReservationDTO;
import com.example.PlaceAdminister.Model_Entitiy.*;
import com.example.PlaceAdminister.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TableReservationService {

    @Autowired
    TableReservationRepository tableReservationRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    TableRepository tableRepository;

    public List<TableReservation> getAllByPlaceId(Long id){

        PlaceEntity place = placeRepository.getById(id);

        // Ensure the place exists before querying reservations
        if (place != null) {
            // Access the ID property of the PlaceEntity and use it for the query
            List<TableReservation> reservations = tableReservationRepository.findByPlaceId(place.getId());
            System.out.println(reservations.size());
            return reservations;
        } else {
            // Handle the case where the place with the provided ID doesn't exist
            // You can return an empty list, throw an exception, or log an error message
            return Collections.emptyList();
        }
    }

    public List<TableReservation> getAllByPlaceIdAtTime(Long id , LocalDateTime time){
        PlaceEntity place = placeRepository.getById(id);
        LocalDateTime startTime = time;
        System.out.println("start time is:" + time);
        System.out.println("end time is:" + time.plusHours(1));
//        LocalDateTime endTime = time;
        if (place != null) {
            List<TableReservation> reservations = tableReservationRepository.getTimeReservations(id ,time,startTime,time.plusHours(1));
            System.out.println(List.of(reservations));
            System.out.println(reservations.size());
            return reservations;
        } else {
            // Handle the case where the place with the provided ID doesn't exist
            // You can return an empty list, throw an exception, or log an error message
            return Collections.emptyList();
        }
    }

    @Scheduled(fixedRate = 5000)
    public void checkIfReservationMissed(){
        LocalDateTime localDateTime = LocalDateTime.now();

        List<TableReservation> allReservations =  tableReservationRepository.findAll();

        allReservations.forEach(nowReservation ->{
            if(nowReservation.getTime().getHour()
                    == localDateTime.getHour()
                    && nowReservation.getStatus() == 1){
                nowReservation.setStatus(3);
                System.out.println("status is:" + nowReservation.getStatus());
                tableReservationRepository.save(nowReservation);
            }
        });
    }


    public TableReservation takeReservation(Long id) {
        TableReservation reservation = tableReservationRepository.getById(id);
        if(tableReservationRepository.existsById(id)){

            reservation.setPeriod_of_reservations(reservation.getPeriod_of_reservations());
            reservation.setTime(reservation.getTime());
            reservation.setNumber_of_seats(reservation.getNumber_of_seats());
            reservation.setPlace(reservation.getPlace());
            reservation.setTable(reservation.getTable());
            reservation.setStatus(2);
            tableReservationRepository.save(reservation);
        }
        return reservation;
    }

    public TableReservation reserve(TableReservationDTO reservationDTO){

        PlaceEntity place = placeRepository.getById(reservationDTO.getPlace_id());
        TableEntity table = tableRepository.getById(reservationDTO.getTable_id());
        if(table.getPlace() != place){
            return  null;
        }
        TableReservation existedReservation  = tableReservationRepository.findAll().stream()
                .filter(i->i.getTable().getId().equals(reservationDTO.getTable_id()))
                .filter(l->l.getTime().getDayOfYear() == reservationDTO.getTime().getDayOfYear())
                .filter(j->(j.getTime().getHour() == reservationDTO.getTime().getHour())
                        || (j.getTime().getHour() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHour()))
                .findFirst().orElse(null);

        if(existedReservation != null){
            return null;
        }
        TableReservation reservation = new TableReservation(reservationDTO);
        reservation.setTable(table);
        reservation.setPlace(place);
        return tableReservationRepository.save(reservation);
    }


    public TableReservation getReservation(Long id){
        TableReservation reservation = null;
        if(tableReservationRepository.existsById(id))
            reservation = tableReservationRepository.getById(id);

        return reservation;
    }

    public TableReservation updateReservation(TableReservationDTO reservationDTO , Long id)
    {
        if(tableReservationRepository.existsById(id)){
            TableReservation reservation  = tableReservationRepository.getById(id);
            PlaceEntity place = placeRepository.getById(reservationDTO.getPlace_id());
            TableEntity table = tableRepository.getById(reservationDTO.getTable_id());

            TableReservation existedReservation  = tableReservationRepository.findAll().stream()
                    .filter(i->i.getTable().getId().equals(reservationDTO.getTable_id()))
                    .filter(l->l.getTime().getDayOfYear() == reservationDTO.getTime().getDayOfYear())
                    .filter(j->(j.getTime().getHour() == reservationDTO.getTime().getHour())
                            || (j.getTime().getHour() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHour()))                    .filter(k->k.getId() != reservation.getId())
                    .findFirst().orElse(null);

            if(existedReservation != null){
                return null;
            }

            if(placeRepository.existsById(reservationDTO.getPlace_id())
                    && tableRepository.existsById(reservationDTO.getTable_id()))
            {
                reservation.setPeriod_of_reservations(reservationDTO.getPeriod_of_reservations());
                reservation.setTime(reservationDTO.getTime());
                reservation.setNumber_of_seats(reservationDTO.getNumber_of_seats());
                reservation.setPlace(place);
                reservation.setTable(table);
                tableReservationRepository.save(reservation);
                return reservation;
            }
        }
        return null;
    }

    public Boolean cancelReservation(Long id)
    {
        if(tableReservationRepository.existsById(id)){
            tableReservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
