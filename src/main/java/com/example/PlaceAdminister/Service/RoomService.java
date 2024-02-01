package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class RoomService {

    public List<RoomEntity> getAllRooms() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<RoomEntity> models = objectMapper.readValue(new File("src/main/resources/Rooms.json"), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void addRoom(RoomEntity room) {
        //rooms.add(room);
        // Save the updated list to JSON
    }
    record NewRoomRequset(
            String name,
            String email,
            Integer age
    ){
    }
    public List<RoomEntity> readFromJsonFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<RoomEntity> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public void writeToJsonFile(List<RoomEntity> models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), models);
        } catch (IOException e) {

        }
    }
}

