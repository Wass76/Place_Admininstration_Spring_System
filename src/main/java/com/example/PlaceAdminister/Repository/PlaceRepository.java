package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
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
    public List<PlaceDTO> readFromJsonFile(Resource resource) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<PlaceDTO> models = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public PlaceDTO writeToJsonFile(PlaceDTO models, Resource resource) {

            try {
                ObjectMapper objectMapper = new ObjectMapper();

                // Read existing data from the JSON file
                List<PlaceDTO> places = readFromJsonFile(resource);

                // Generate ID for the new model
                Long id = places.isEmpty() ? 1 : places.get(places.size() - 1).getId() + 1;
                models.setId(id);

                // Add the new model to the existing list
                places.add(models);

                // Write the updated list back to the JSON file
                objectMapper.writeValue(new File(resource.getURI()), places);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately in a production environment
            }
            return models;
        }

    public PlaceDTO searchDataById(Long id , Resource resource) {
        List<PlaceDTO> dataList = readFromJsonFile(resource);
        return dataList.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public PlaceDTO UpdateById(Long id , PlaceDTO place , Resource resource){
        try {
            // Step 1: Read the JSON file and parse it
            File jsonFile = new File(resource.getFilename());
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
