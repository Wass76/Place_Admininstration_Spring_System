package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.RoomReservationDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomReservation;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import com.example.PlaceAdminister.Repository.RoomRepository;
import com.example.PlaceAdminister.Repository.RoomReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoomReservationService {
    @Autowired
    RoomReservationRepository roomReservationRepository;
    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    RoomRepository roomRepository;

    public List<RoomReservation> getAllByPlaceId(Long id){

        PlaceEntity place = placeRepository.getById(id);

        // Ensure the place exists before querying reservations
        if (place != null) {
            // Access the ID property of the PlaceEntity and use it for the query
            List<RoomReservation> reservations = roomReservationRepository.findByPlaceId(place.getId());
            System.out.println(reservations.size());
            return reservations;
        } else {
            // Handle the case where the place with the provided ID doesn't exist
            // You can return an empty list, throw an exception, or log an error message
            return Collections.emptyList();
        }
    }

//    @Scheduled(fixedRate = 5000)
    public void checkIfReservationMissed()
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<RoomReservation> allReservations =  roomReservationRepository.findAll();
        allReservations.forEach(nowReservation ->{
            if(nowReservation.getTime().getHour()
                    == localDateTime.getHour()
                    && nowReservation.getStatus() == 1){
                nowReservation.setStatus(3);
                System.out.println("status is:" + nowReservation.getStatus());
                roomReservationRepository.save(nowReservation);
            }
        });
    }


    public RoomReservation takeReservation(Long id) {
        RoomReservation reservation = roomReservationRepository.getById(id);
        if(roomReservationRepository.existsById(id)){

            reservation.setPeriod_of_reservations(reservation.getPeriod_of_reservations());
            reservation.setTime(reservation.getTime());
            reservation.setNumber_of_seats(reservation.getNumber_of_seats());
            reservation.setPlace(reservation.getPlace());
            reservation.setRoom(reservation.getRoom());

            reservation.setStatus(2);
            roomReservationRepository.save(reservation);
        }
        return reservation;
    }

    public List<RoomReservation> getAllByPlaceIdAtTime(Long id , LocalDateTime time){
        PlaceEntity place = placeRepository.getById(id);
        LocalDateTime startTime = time;
        System.out.println("start time is:" + time);
        System.out.println("end time is:" + time.plusHours(1));
//        LocalDateTime endTime = time;
        if (place != null) {
            List<RoomReservation> reservations = roomReservationRepository.getTimeReservations(id ,time,startTime,time.plusHours(1));
            System.out.println(List.of(reservations));
            System.out.println(reservations.size());
            return reservations;
        } else {
            // Handle the case where the place with the provided ID doesn't exist
            // You can return an empty list, throw an exception, or log an error message
            return Collections.emptyList();
        }
    }

    public RoomReservation reserve(RoomReservationDTO reservationDTO){

//        List<RoomReservation> existedReservations = roomReservationRepository.findByTwoColumns(reservationDTO.getTime() , reservationDTO.getRoom_id());
//        if(existedReservations.size()>0){
//            return null;
//        }
        PlaceEntity place = placeRepository.getById(reservationDTO.getPlace_id());
        RoomEntity room = roomRepository.getById(reservationDTO.getRoom_id());
        if(room.getPlace() != place){
            return  null;
        }
        RoomReservation existedReservation  = roomReservationRepository.findAll().stream()
                .filter(i->i.getRoom().getId().equals(reservationDTO.getRoom_id()))
                .filter(l->l.getTime().getDayOfYear() == reservationDTO.getTime().getDayOfYear())
                .filter(j->(j.getTime().getHour() == reservationDTO.getTime().getHour())
                        || (j.getTime().getHour() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHour()))
                .findFirst().orElse(null);

        if(existedReservation != null){
            return null;
        }
        RoomReservation reservation = new RoomReservation(reservationDTO);
        reservation.setRoom(room);
        reservation.setPlace(place);
        return roomReservationRepository.save(reservation);
    }


    public RoomReservation getReservation(Long id){
        RoomReservation reservation = null;
        if(roomReservationRepository.existsById(id))
             reservation = roomReservationRepository.getById(id);

        return reservation;
    }



    public RoomReservation updateReservation(RoomReservationDTO reservationDTO , Long id)
    {
        if(roomReservationRepository.existsById(id)){
            RoomReservation reservation  = roomReservationRepository.getById(id);
            PlaceEntity place = placeRepository.getById(reservationDTO.getPlace_id());
            RoomEntity room = roomRepository.getById(reservationDTO.getRoom_id());

            RoomReservation existedReservation  = roomReservationRepository.findAll().stream()
                    .filter(i->i.getRoom().getId().equals(reservationDTO.getRoom_id()))
                    .filter(l->l.getTime().getDayOfYear() == reservationDTO.getTime().getDayOfYear())
                    .filter(j->(j.getTime().getHour() == reservationDTO.getTime().getHour())
                            || (j.getTime().getHour() + j.getPeriod_of_reservations() -1 == reservationDTO.getTime().getHour()))
                    .filter(k->k.getId() != reservation.getId())
                    .findFirst().orElse(null);

            if(existedReservation != null){
                return null;
            }

            if(placeRepository.existsById(reservationDTO.getPlace_id())
                    && roomRepository.existsById(reservationDTO.getRoom_id()))
            {
                reservation.setPeriod_of_reservations(reservationDTO.getPeriod_of_reservations());
                reservation.setTime(reservationDTO.getTime());
                reservation.setNumber_of_seats(reservationDTO.getNumber_of_seats());
                reservation.setPlace(place);
                reservation.setRoom(room);
                roomReservationRepository.save(reservation);
                return reservation;
            }
        }
        return null;
    }

    public Boolean cancelReservation(Long id)
    {
        if(roomReservationRepository.existsById(id)){
            roomReservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
