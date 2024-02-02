package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.DTO.TableDTO;
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
public class TableRepository {

    public List<TableDTO> readFromJsonFile(String filePath) {
        String filepath1 = "src/main/resources/Rooms.json";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<TableDTO> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public TableDTO writeToJsonFile(TableDTO models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<TableDTO> tables= readFromJsonFile(filePath);
            models.setId((long)tables.size()+1);
            tables.add(models);

            objectMapper.writeValue(new File(filePath), tables);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }


    public TableDTO searchDataById(Long id , String filePath) {
        List<TableDTO> dataList = readFromJsonFile(filePath);
        return dataList.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<TableDTO> searchByRoomId( Long room_id, String filePath) {
        List<TableDTO> dataList = readFromJsonFile(filePath);
         List<TableDTO> tableDTOList =  dataList.stream()
                .filter(data -> data.getRoom_id().equals(room_id)).collect(Collectors.toList());
         return tableDTOList;
//                .orElse(null)) ;
    }

    public List<TableDTO> searchByCategoryId( Long category_id, String filePath) {
        List<TableDTO> dataList = readFromJsonFile(filePath);
        List<TableDTO> tableDTOList =  dataList.stream()
                .filter(data -> data.getCategory_id().equals(category_id)).collect(Collectors.toList());
        return tableDTOList;
//                .orElse(null)) ;
    }


    public TableDTO UpdateById(Long id , TableDTO tableDTO , String filePath){
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
                    element.put("id" , id);
                    element.put("status", tableDTO.getStatus());
                    element.put("time_of_reservation", tableDTO.getTime_of_reservation());
                    element.put("room_id" ,tableDTO.getRoom_id());
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

        return tableDTO;
    }

//    public List<TableDTO> searchData( Long room_id, String filePath) {
//        List<TableDTO> dataList = readFromJsonFile(filePath);
//        return dataList.stream()
//                .filter(data -> data.getName().contains(keyword))
//                .collect(Collectors.toList());
//    }



}
