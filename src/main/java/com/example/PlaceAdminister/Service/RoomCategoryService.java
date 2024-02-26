package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomCategoryService {
    @Autowired
    private RoomCategoryEntity roomCategories;
    @Autowired
    private RoomCategoryRepository roomCategoryRepository;

    private final ResourceLoader resourceLoader;
    private String filepath = "src/main/resources/RoomCategories.json";

    public RoomCategoryService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public List<RoomCategoryDTO> getAllRoomCategories(Long id) {
        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");

        List<RoomCategoryDTO> roomCategoryDTOList =    roomCategoryRepository.readFromJsonFile(resource);
           List<RoomCategoryDTO> myRoomCategoryList = roomCategoryDTOList.stream().filter(i->i.getPlace_id().equals(id)).toList();
           return myRoomCategoryList;
    }

    public RoomCategoryDTO store(RoomCategoryDTO roomCategoryDTO) {
        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");
        return roomCategoryRepository.writeToJsonFile(roomCategoryDTO ,resource);
    }

    public RoomCategoryDTO getRoomCategory(Long id)
    {
        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");
        return roomCategoryRepository.searchDataById(id , resource);
    }

    public RoomCategoryDTO update(Long id , RoomCategoryDTO roomCategoryDTO){
        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");
        return roomCategoryRepository.UpdateById(id ,roomCategoryDTO,resource);
    }

    public void delete(Long id){
        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");
        roomCategoryRepository.deleteById(id,resource);
    }

}
