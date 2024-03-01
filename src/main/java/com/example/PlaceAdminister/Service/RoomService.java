package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.RoomDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomCategoryEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import com.example.PlaceAdminister.Repository.RoomCategoryRepository;
import com.example.PlaceAdminister.Repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class RoomService {
    @Autowired
    private RoomEntity rooms;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomCategoryRepository roomCategoryRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceService placeService;
    private String roomFilepath = "src/main/resources/Rooms.json";
    private String placeFilepath = "src/main/resources/Places.json";



    public List<RoomEntity> getAllRooms(Long id) {
//        Resource resource = resourceLoader.getResource("classpath:Rooms.json");

        List<RoomEntity> roomList = roomRepository.findByPlaceId(id);
//        List<RoomEntity> myRoomList = roomList.stream().filter(i->i.getPlace().equals(id)).toList();
//         List<RoomDTO> myRoomList = roomList.stream().filter(i->i.getPlaceId().equals(id)).toList();
        return roomList;

    }

    public RoomEntity store(RoomDTO roomDTO)
    {
//        RoomEntity room = new RoomEntity(roomDTO);
//
//        System.out.println("place: " + roomDTO.getPlace_id());
//        System.out.println("room category: " +roomDTO.getCategory_id() );
//        room.setPlace(placeRepository.getById(roomDTO.getPlace_id()));
//        room.setRoomCategory(roomCategoryRepository.getById(roomDTO.getCategory_id()));
//        System.out.println(room.getRoomCategory().getId());
//
////        RoomCategoryEntity roomCategory =  roomCategoryRepository.getReferenceById(roomDTO.getCategory_id());
////        roomCategory.setRooms();
//        return roomRepository.save(room);

        try {
            PlaceEntity place = placeRepository.getById(roomDTO.getPlace_id()); // Might throw EntityNotFoundException
            RoomCategoryEntity roomCategory = roomCategoryRepository.getById(roomDTO.getCategory_id()); // Might throw EntityNotFoundException

            if(roomCategory.getPlace().getId() != place.getId()){
                throw new RuntimeException("unLogical operation");
            }

            RoomEntity room = new RoomEntity(roomDTO);
            room.setPlace(place);
            room.setRoomCategory(roomCategory);


            return roomRepository.save(room);
        } catch (EntityNotFoundException e) {
            // Handle missing references gracefully, e.g., log the error and return a null or error response
        }
        return  null;
    }

    public RoomEntity getItem(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:Rooms.json");
        RoomEntity room =   roomRepository.getById(id );
        return room;
    }

    public List<RoomEntity>  getByCategory(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:Rooms.json");
       List<RoomEntity>  rooms =   roomRepository.findByRoomCategoryId(id );
        return rooms;
    }

    public RoomEntity update(Long id , RoomDTO roomDTO){
//        Resource resource = resourceLoader.getResource("classpath:Rooms.json");
        RoomEntity room = roomRepository.getById(id);
        if(room != null)
        {
          PlaceEntity place = placeService.getById(roomDTO.getPlace_id());
            room.setStatus(roomDTO.getStatus());
            room.setMax_num_of_chairs(roomDTO.getMax_num_of_chairs());
            room.setRoomCategory(roomCategoryRepository.getById(roomDTO.getCategory_id()));
            roomRepository.save(room);
        }
        return room;
    }

    public List<RoomEntity> showRoomsByPlaceId(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:Rooms.json");
        return  roomRepository.findAll().stream().filter(i->i.getPlace().getId().equals(id)).toList();
    }

    public void delete(Long id){
//        Resource resource = resourceLoader.getResource("classpath:Rooms.json");
        RoomEntity room = roomRepository.getReferenceById(id);
        roomRepository.deleteById(id);
    }
}

