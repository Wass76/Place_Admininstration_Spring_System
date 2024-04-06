package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public interface PlaceRepository extends JpaRepository<PlaceEntity,Long> {

    PlaceEntity getByName(String name);

}
