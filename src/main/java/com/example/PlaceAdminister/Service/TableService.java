package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.TableDTO;
import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

    private TableEntity tables;

    public List<TableEntity> index(){
        return List.of(tables);
    }



    public static List<TableDTO> readFromJsonFile(String filePath) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            List<TableDTO> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
//            return models;
//        } catch (IOException e) {
//            e.printStackTrace(); // Handle the exception appropriately in a production environment
//            return null;
//        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(filePath);
            if (file.exists() && file.length() > 0) {
                return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, TableDTO.class));
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<TableDTO> writeToJsonFile(List<TableDTO>  models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), models);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }

    // Existing data in the JSON file

    // New data to be added


    // Combine existing and new data
//    List<TableDTO> combinedData = new ArrayList<>(existingData);
//        combinedData.add(newData);
//
//    // Write combined data back to the file
//    writeDataToFile("Table.json", combinedData);


    public static List<TableDTO> writeDataToFile(String fileName, List<TableDTO>  data) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<TableDTO> existingData = readFromJsonFile(fileName);
//            System.out.println(existingData.);
            List<List<TableDTO> > combinedData = new ArrayList<>(List.of(existingData) );

            combinedData.add(data);

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(new File(fileName), data);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
        return data;
    }
}
