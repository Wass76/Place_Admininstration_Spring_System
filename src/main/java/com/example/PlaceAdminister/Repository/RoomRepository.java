package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.DTO.RoomDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomRepository {

    public List<RoomDTO> readFromJsonFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<RoomDTO> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public RoomDTO writeToJsonFile(RoomDTO models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<RoomDTO> rooms= readFromJsonFile(filePath);
            Long id=(Long)rooms.get(rooms.size()+1).getId();
            models.setId(id);
            rooms.add(models);

            objectMapper.writeValue(new File(filePath), rooms);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }


    public RoomDTO searchDataById(Long id , String filePath) {
        List<RoomDTO> dataList = readFromJsonFile(filePath);
        return dataList.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

//    public List<RoomDTO> searchByRoomId( Long room_id, String filePath) {
//        List<RoomDTO> dataList = readFromJsonFile(filePath);
//        List<RoomDTO> roomDTOList =  dataList.stream()
//                .filter(data -> data.getRoom_id().equals(room_id)).collect(Collectors.toList());
//        return roomDTOList;
////                .orElse(null)) ;
//    }

//    public List<RoomDTO> searchByCategoryId( Long category_id, String filePath) {
//        List<RoomDTO> dataList = readFromJsonFile(filePath);
//        List<RoomDTO> roomDTOList =  dataList.stream()
//                .filter(data -> data.getCategory_id().equals(category_id)).collect(Collectors.toList());
//        return roomDTOList;
////                .orElse(null)) ;
//    }


    public RoomDTO UpdateById(Long id , RoomDTO roomDTO , String filePath) {
        try {
            // Step 1: Read the JSON file and parse it
            File jsonFile = new File(filePath);
            FileInputStream fis = new FileInputStream(jsonFile);
            JSONTokener tokener = new JSONTokener(fis);
            JSONArray jsonArray = new JSONArray(tokener);

            // Step 2 and 3: Identify and update the specific element
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject element = jsonArray.getJSONObject(i);
                if (element.getLong("id") == (id)) { // Assuming "id" is the identifier for the element
                    System.out.println(element.getInt("id"));
                    element.put("id", id);
                    element.put("status", roomDTO.getStatus());
                    element.put("time_of_reservation", roomDTO.getTime_of_reservation());
//                    element.put("room_id", roomDTO.getRoom_id());
                    // Add more modifications as needed
                }
            }

            // Step 4: Write the updated data back to the JSON file
            FileWriter fileWriter = new FileWriter(jsonFile);
            jsonArray.write(fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return roomDTO;


    }
}