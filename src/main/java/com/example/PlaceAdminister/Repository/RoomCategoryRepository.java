package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomCategoryRepository extends AbstractRepository{
    public List<RoomCategoryDTO> readFromJsonFile(Resource resource) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<RoomCategoryDTO> models = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public RoomCategoryDTO writeToJsonFile(RoomCategoryDTO models, Resource resource) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<RoomCategoryDTO> roomCategory= readFromJsonFile(resource);
            Long id= Long.valueOf(1);
            if(!(roomCategory.size()==0)) id=(Long)roomCategory.get(roomCategory.size()-1).getId()+1;
            models.setId(id);
            roomCategory.add(models);

            File file = resource.getFile();

            objectMapper.writeValue(file, roomCategory);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }
    public RoomCategoryDTO searchDataById(Long id , Resource resource) {
        List<RoomCategoryDTO> dataList = readFromJsonFile(resource);
        return dataList.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public RoomCategoryDTO UpdateById(Long id , RoomCategoryDTO roomCategoryDTO , Resource resource){
        try {
            // Step 1: Read the JSON file and parse it
            File jsonFile = resource.getFile();
            FileInputStream fis = new FileInputStream(jsonFile);
            JSONTokener tokener = new JSONTokener(fis);
            JSONArray jsonArray = new JSONArray(tokener);

            // Step 2 and 3: Identify and update the specific element
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject element = jsonArray.getJSONObject(i);
                if (element.getLong("id") == (id)) { // Assuming "id" is the identifier for the element
                    System.out.println(element.getInt("id"));
                    element.put("id" , id);
                    element.put("type" , roomCategoryDTO.getType());

                }
            }

            FileWriter fileWriter = new FileWriter(jsonFile);
            jsonArray.write(fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return roomCategoryDTO;
    }
}
