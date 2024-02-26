package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomService {
    @Autowired
    private RoomEntity rooms;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomCategoryService roomCategoryService;

    private final ResourceLoader resourceLoader;

    @Autowired
    private PlaceService placeService;
    private String roomFilepath = "src/main/resources/Rooms.json";
    private String placeFilepath = "src/main/resources/Places.json";

    public RoomService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public List<RoomDTO> getAllRooms(Long id) {
        Resource resource = resourceLoader.getResource("classpath:Rooms.json");

        List<RoomDTO> roomList = roomRepository.readFromJsonFile(resource);
         List<RoomDTO> myRoomList = roomList.stream().filter(i->i.getPlaceId().equals(id)).toList();
        return myRoomList;

    }

    public RoomDTO store(RoomDTO roomDTO)
    {
        Resource resource = resourceLoader.getResource("classpath:Rooms.json");

        if(roomCategoryService.getAllRoomCategories(roomDTO.getPlaceId()).size() == 0){
            return new RoomDTO("you should add some room category first");
        }
        if(placeService.getAllPlaces().size() ==0){
            return new RoomDTO("you should add some place first");
        }
        return roomRepository.writeToJsonFile(roomDTO ,resource);
    }

    public RoomDTO getItem(Long id)
    {
        Resource resource = resourceLoader.getResource("classpath:Rooms.json");
        RoomDTO room =   roomRepository.searchDataById(id , resource);
        return room;
    }


    public RoomDTO update(Long id , RoomDTO roomDTO){
        Resource resource = resourceLoader.getResource("classpath:Rooms.json");
        return roomRepository.UpdateById(id ,roomDTO,resource);
    }

    public List<RoomDTO> showRoomsByPlaceId(Long id)
    {
        Resource resource = resourceLoader.getResource("classpath:Rooms.json");
        return  roomRepository.searchByPlaceId(id , resource);
    }

    public void delete(Long id){
        Resource resource = resourceLoader.getResource("classpath:Rooms.json");
        roomRepository.deleteById(id,resource);
    }
}

