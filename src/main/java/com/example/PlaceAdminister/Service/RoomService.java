package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class RoomService {
    private List<RoomEntity> rooms; // Assuming you load/save this list from/to JSON

    public List<RoomEntity> getAllRooms() {
        return rooms;
    }

    public void addRoom(RoomEntity room) {
        rooms.add(room);
        // Save the updated list to JSON
    }
    record NewRoomRequset(
            String name,
            String email,
            Integer age
    ){
    }
    // Other methods as needed
    public List<RoomEntity> readFromJsonFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<RoomEntity> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
            return null;
        }
    }
    public void writeToJsonFile(List<RoomEntity> models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), models);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
    }
}

