package com.example.PlaceAdminister;

import Model_Entitiy.RoomEntity;
import Model_Entitiy.RoomCategoryEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class JsonDealer {
    public static void readFromJson(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read from rooms.json
//            List rooms = objectMapper.readValue(new File("rooms.json"), new TypeReference<List<RoomEntity>>() {});

            // Read from roomCategories.json
            List roomCategories = objectMapper.readValue(new File("roomCategories.json"), new TypeReference<>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Create sample data
            java.util.List<RoomEntity> rooms = new ArrayList<>();
            // Populate rooms as needed

            java.util.List<RoomCategoryEntity> roomCategories = new ArrayList<>();
            // Populate roomCategories as needed

            // Write to rooms.json
            objectMapper.writeValue(new File("rooms.json"), rooms);

            // Write to roomCategories.json
            objectMapper.writeValue(new File("roomCategories.json"), roomCategories);

            System.out.println("Data written to JSON files successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
