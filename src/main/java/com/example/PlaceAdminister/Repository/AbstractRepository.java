package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
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
public class AbstractRepository {

    public List<ReservationDTO> readFromJsonReservation(Resource resource) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ReservationDTO> models = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public ReservationDTO writeToJsonReservation(ReservationDTO models, Resource resource) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<ReservationDTO> reservations= readFromJsonReservation(resource);
            models.setId((long)reservations.size()+1);
            reservations.add(models);

            File file = resource.getFile();

            objectMapper.writeValue(file, reservations);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }

    public ReservationDTO findDataById(Long id , Resource resource) {
        List<ReservationDTO> dataList = readFromJsonReservation(resource);
        return dataList.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public ReservationDTO reserveTable(ReservationDTO reservationDTO , Resource resource){
        if(reservationDTO!= null){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
//                System.out.println(reservationDTO.getPeriod_of_reservations());
                List<ReservationDTO> tables= readFromJsonReservation(resource);
                if(tables.stream().
                        filter(i ->i.getTable_id()
                                .equals(reservationDTO.getTable_id()))
                        .findFirst().stream().toList() !=null){
                Long id= Long.valueOf(1);
                if(!(tables.size()==0)) id=(Long)tables.get(tables.size()-1).getId()+1;
                reservationDTO.setId(id);}
                System.out.println();
                tables.add(reservationDTO);

                File file = resource.getFile();

                objectMapper.writeValue(file, tables);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately in a production environment
            }
            return reservationDTO;}
        else
            return  null;
    }

    public ReservationDTO UpdateById(Long id , ReservationDTO reservationDTO , Resource resource){
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
                    element.put("period_of_reservations", reservationDTO.getPeriod_of_reservations());
                    element.put("time", reservationDTO.getTime());
                    element.put("room_id" ,reservationDTO.getRoom_id());
                    element.put("table_id" ,reservationDTO.getTable_id());
                    element.put("num_of_seats" , reservationDTO.getNum_of_seats());
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

        return reservationDTO;
    }

    public void deleteById(Long id , Resource resource){
        try {
            // Step 1: Read the JSON file and parse it
            File jsonFile = resource.getFile();
            FileInputStream fis = new FileInputStream(jsonFile);
            JSONTokener tokener = new JSONTokener(fis);
            JSONArray jsonArray = new JSONArray(tokener);
            fis.close(); // Close the FileInputStream

            // Step 2: Identify and remove the specific element
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject element = jsonArray.getJSONObject(i);
                if (element.getLong("id") == id) {
                    jsonArray.remove(i); // Remove the JSONObject at index i
                    break; // Exit the loop once the element is removed
                }
            }

            // Step 3: Write the updated data back to the JSON file
            FileWriter fileWriter = new FileWriter(jsonFile);
            jsonArray.write(fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
