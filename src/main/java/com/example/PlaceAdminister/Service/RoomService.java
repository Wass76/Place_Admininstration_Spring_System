package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Repository.RoomRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class RoomService {
    @Autowired
    private RoomEntity rooms;
    @Autowired
    private RoomRepository roomRepository;
    private String filepath = "src/main/resources/Rooms.json";


    public List<RoomDTO> getAllRooms() {
        return roomRepository.readFromJsonFile(filepath);

    }

    public RoomDTO store(RoomDTO roomDTO) {
        return roomRepository.writeToJsonFile(roomDTO ,this.filepath);
    }

    public RoomDTO show(Long id)
    {
        return roomRepository.searchDataById(id , this.filepath);
    }

//    public List<RoomDTO> showTablesByRoomId(Long id)
//    {
//        return  roomRepository.searchByRoomId(id , this.filepath);
//    }
//
//    public List<RoomDTO> showTablesByCategoryId(Long id)
//    {
//        return  roomRepository.searchByCategoryId(id , this.filepath);
//    }

    public RoomDTO update(Long id , RoomDTO roomDTO){
        return roomRepository.UpdateById(id ,roomDTO,this.filepath);
    }


    public RoomDTO reserveRoom(Long id , Date date){
        RoomDTO roomDTO = roomRepository.searchDataById(id ,filepath);
        if(roomDTO != null){
            if(roomDTO.getStatus()!=1) {
                roomDTO.setStatus(2);
                roomDTO.setTime_of_reservation(date);
                roomRepository.UpdateById(id, roomDTO, filepath);
                return roomDTO;
            }
            else return null;
        }
        else {
            return null;
        }
    }

    public RoomDTO cancelRoomReservation(Long id ) {
        RoomDTO roomDTO = roomRepository.searchDataById(id, filepath);
        if (roomDTO != null) {
//            if(tableDTO.getStatus() == 2){
            roomDTO.setStatus(1);
            roomDTO.setTime_of_reservation(null);

            roomRepository.UpdateById(id, roomDTO, filepath);
            return roomDTO;
        } else {
            return null;
        }
    }


    public void delete(Long id){

    }

}

