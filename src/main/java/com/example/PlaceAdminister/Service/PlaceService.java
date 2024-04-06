package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceEntity placeEntity;
    @Autowired
    private PlaceRepository placeRepository;
    private final String filepath = "src/main/resources/Places.json";




@Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<PlaceEntity> getAllPlaces() {
//        Resource resource = resourceLoader.getResource("classpath:Places.json");
        return placeRepository.findAll();

    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PlaceEntity store(PlaceDTO placeDTO) throws IOException {
        PlaceEntity place = new PlaceEntity(placeDTO);
//        Resource resource = resourceLoader.getResource("classpath:Places.json");
        return placeRepository.save(place);
    }

    public PlaceEntity getById(Long id)
    {
//        Resource resource = resourceLoader.getResource("classpath:Places.json");
//        System.out.println( id);

//        PlaceEntity place = (placeRepository.getReferenceById(id));
////        if(place.isPresent())
//             return placeRepository.getById(id);
        Optional<PlaceEntity> placeOptional = placeRepository.findById(id);
        return placeOptional.orElse(null);
//        else
//            return null;

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PlaceEntity update(Long id , PlaceDTO placeDTO){
//        Resource resource = resourceLoader.getResource("classpath:Places.json");
            PlaceEntity place =placeRepository.getReferenceById(id);
            if(place != null){
                place.setName(placeDTO.getName());
                place.setLocation(placeDTO.getLocations());
//                place.setImage(null);
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
