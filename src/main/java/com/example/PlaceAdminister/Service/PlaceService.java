package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceEntity placeEntity;
    @Autowired
    private PlaceRepository placeRepository;
    private final String filepath = "src/main/resources/Places.json";



    public List<PlaceEntity> getAllPlaces() {
//        Resource resource = resourceLoader.getResource("classpath:Places.json");
        return placeRepository.findAll();

    }

    public PlaceEntity store(PlaceDTO placeDTO) {
        PlaceEntity place = new PlaceEntity(placeDTO);
//        Resource resource = resourceLoader.getResource("classpath:Places.json");
        return placeRepository.save(place);
    }

    public PlaceEntity getById(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:Places.json");
//        System.out.println( id);

        PlaceEntity place = (placeRepository.getReferenceById(id));
//        if(place.isPresent())
             return placeRepository.getById(id);
//        else
//            return null;


    }


    public PlaceEntity update(Long id , PlaceDTO placeDTO){
//        Resource resource = resourceLoader.getResource("classpath:Places.json");
            PlaceEntity place =placeRepository.getById(id);
            if(place != null){
                place.setName(placeDTO.getName());
                place.setLocation(placeDTO.getLocations());
                placeRepository.save(place);
            }
            return place;
    }

    public void delete(Long id){
//        Resource resource = resourceLoader.getResource("classpath:Places.json");

        PlaceEntity place = placeRepository.getReferenceById(id);
        placeRepository.delete(place);
    }
}
