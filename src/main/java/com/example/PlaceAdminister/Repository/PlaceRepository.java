package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
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
@Component
public class PlaceRepository extends AbstractRepository {
    public List<PlaceDTO> readFromJsonFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<PlaceDTO> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public PlaceDTO writeToJsonFile(PlaceDTO models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<PlaceDTO> place= readFromJsonFile(filePath);
            Long id= Long.valueOf(1);
            if(!(place.size()==0)) id=(Long)place.get(place.size()-1).getId()+1;
            models.setId(id);
            place.add(models);

            objectMapper.writeValue(new File(filePath), place);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }

    public PlaceDTO searchDataById(Long id , String filePath) {
        List<PlaceDTO> dataList = readFromJsonFile(filePath);
        return dataList.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public PlaceDTO UpdateById(Long id , PlaceDTO place , String filePath){
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
                    element.put("name" , place.getName());
                    element.put("locations" , place.getLocations());
                    element.put("rooms",place.getRooms());
//                    element.put("shape", place.getShape());
//                    element.put("num_of_seats", place.getNum_of_seats());
                    // Add more modifications as needed
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

        return place;
    }


}
