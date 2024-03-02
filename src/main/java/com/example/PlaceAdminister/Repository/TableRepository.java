package com.example.PlaceAdminister.Repository;

//import com.example.PlaceAdminister.DTO.ReservationDTO;
import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.DTO.TableDTO;
//import com.example.PlaceAdminister.Model_Entitiy.ReservationsEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public interface TableRepository extends JpaRepository<TableEntity,Long> {

    List<TableEntity> findByPlaceId(Long placeId);

//    List<TableEntity> findByTableCategoryId(Long tableCategoryId);
//    List<TableEntity> findByRoomId(Long roomId);

//
//    public List<TableDTO> readFromJsonTable(Resource resource) {
//        String filepath1 = "src/main/resources/Rooms.json";
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            List<TableDTO> models = objectMapper.readValue(resource.getFile(), new TypeReference<>() {});
//            return models;
//        } catch (IOException e) {
//            return new ArrayList<>();
//        }
//    }
//
//    public TableDTO writeToJsonTable(TableDTO models, Resource resource) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            List<TableDTO> tables= readFromJsonTable(resource);
//            Long id= Long.valueOf(1);
//            if(!(tables.size()==0)) id=(Long)tables.get(tables.size()-1).getId()+1;
//            models.setId(id);
//            tables.add(models);
//
//            File file = resource.getFile();
//
//            objectMapper.writeValue(file, tables);
//
//        } catch (IOException e) {
//            e.printStackTrace(); // Handle the exception appropriately in a production environment
//        }
//        return models;
//    }
//
//    public TableDTO searchDataById(Long id , Resource resource) {
//        List<TableDTO> dataList = readFromJsonTable(resource);
//        return dataList.stream()
//                .filter(data -> data.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<TableDTO> searchByRoomId( Long room_id, Resource resource) {
//        List<TableDTO> dataList = readFromJsonTable(resource);
//         List<TableDTO> tableDTOList =  dataList.stream()
//                .filter(data -> data.getRoom_id().equals(room_id)).collect(Collectors.toList());
//         return tableDTOList;
////                .orElse(null)) ;
//    }
//
//    public List<TableDTO> searchByCategoryId( Long category_id, Resource resource)
//    {
//        List<TableDTO> dataList = readFromJsonTable(resource);
//        List<TableDTO> tableDTOList =  dataList.stream()
//                .filter(data -> data.getCategory_id().equals(category_id)).collect(Collectors.toList());
//        return tableDTOList;
////                .orElse(null)) ;
//    }
//
//    public TableDTO UpdateById(Long id , TableDTO tableDTO , Resource resource){
//        try {
//            // Step 1: Read the JSON file and parse it
//            File jsonFile = resource.getFile();
//            FileInputStream fis = new FileInputStream(jsonFile);
//            JSONTokener tokener = new JSONTokener(fis);
//            JSONArray jsonArray = new JSONArray(tokener);
//
//            // Step 2 and 3: Identify and update the specific element
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject element = jsonArray.getJSONObject(i);
//                if (element.getLong("id") == (id)) { // Assuming "id" is the identifier for the element
//                    System.out.println(element.getInt("id"));
//                    element.put("id" , id);
//                    element.put("status", tableDTO.getStatus());
////                    element.put("time_of_reservation", tableDTO.getTime_of_reservation());
//                    element.put("room_id" ,tableDTO.getRoom_id());
//                    // Add more modifications as needed
//                }
//            }
//
//            // Step 4: Write the updated data back to the JSON file
//            FileWriter fileWriter = new FileWriter(jsonFile);
//            jsonArray.write(fileWriter);
//            fileWriter.flush();
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//
//        return tableDTO;
//    }
//
//    public void deleteById(Long id , Resource resource){
//        try {
//            // Step 1: Read the JSON file and parse it
//            File jsonFile = resource.getFile();
//            FileInputStream fis = new FileInputStream(jsonFile);
//            JSONTokener tokener = new JSONTokener(fis);
//            JSONArray jsonArray = new JSONArray(tokener);
//            fis.close(); // Close the FileInputStream
//
//            // Step 2: Identify and remove the specific element
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject element = jsonArray.getJSONObject(i);
//                if (element.getLong("id") == id) {
//                    jsonArray.remove(i); // Remove the JSONObject at index i
//                    break; // Exit the loop once the element is removed
//                }
//            }
//
//            // Step 3: Write the updated data back to the JSON file
//            FileWriter fileWriter = new FileWriter(jsonFile);
//            jsonArray.write(fileWriter);
//            fileWriter.flush();
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//

}
