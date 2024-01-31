package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.Model_Entitiy.TableEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class TableService {

    private TableEntity tables;

    public List<TableEntity> index(){
        return List.of(tables);
    }



    public List<TableEntity> readFromJsonFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<TableEntity> models = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
            return models;
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
            return null;
        }
    }
    public TableEntity writeToJsonFile(TableEntity models, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), models);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
        return models;
    }
}
