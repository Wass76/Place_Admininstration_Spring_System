package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.RoomCategoryDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
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

    private String filepath = "src/main/resources/RoomCategories.json";

    @Autowired
    private PlaceRepository placeRepository;


    public List<RoomCategoryEntity> getAllRoomCategories(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");

        List<RoomCategoryEntity> roomCategoryList = roomCategoryRepository.findByPlaceId(id);
//        List<RoomCategoryEntity> roomCategoryEntityList = roomCategoryList.stream().filter(i->i.getPlace().getId().equals(id)).toList();
//        if(roomCategoryDTOList != null)
//            List<RoomCategoryEntity> myRoomCategoryList = roomCategoryDTOList.stream().filter(i->i.getPlace().getId().equals(id)).toList();
//           List<RoomCategoryEntity> myRoomCategoryList = roomCategoryDTOList.stream().filter(i->i.getPlace().getId().equals(id)).toList();
           return roomCategoryList;
    }

    public List<RoomCategoryEntity> getAllCategories()
    {
        List<RoomCategoryEntity> roomCategoryEntityList = roomCategoryRepository.findAll();
        return roomCategoryEntityList;
    }

    public RoomCategoryEntity store(RoomCategoryDTO roomCategoryDTO) {
//        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");
        if(placeRepository.existsById(roomCategoryDTO.getPlace_id())) {
            PlaceEntity place = placeRepository.getById(roomCategoryDTO.getPlace_id());
            RoomCategoryEntity roomCategory = new RoomCategoryEntity(roomCategoryDTO);
            roomCategory.setPlace(placeRepository.getById(roomCategoryDTO.getPlace_id()));
            return roomCategoryRepository.save(roomCategory);
        }
        return null;
    }

    public RoomCategoryEntity getRoomCategory(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");
        return roomCategoryRepository.getById(id);
    }



    public RoomCategoryEntity update(Long id , RoomCategoryDTO roomCategoryDTO){
//        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");
            RoomCategoryEntity roomCategory = roomCategoryRepository.getById(id);
            if(roomCategory != null){
//                roomCategory.setPlace(roomCategoryDTO.getPlace_id());
                roomCategory.setType(roomCategoryDTO.getType());
//                roomCategory.setNum_of_seats(roomCategoryDTO.getNum_of_seats());
                roomCategoryRepository.save(roomCategory);
            }
        return roomCategory;
    }

    public void delete(Long id){
//        Resource resource = resourceLoader.getResource("classpath:RoomCategories.json");

        roomCategoryRepository.deleteById(id);
    }

}
