package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlaceService {

    @Autowired
    private PlaceEntity placeEntity;
    private final ResourceLoader resourceLoader;
    @Autowired
    private PlaceRepository placeRepository;
    private final String filepath = "src/main/resources/Places.json";

    public PlaceService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public List<PlaceDTO> getAllPlaces() {
        Resource resource = resourceLoader.getResource("classpath:Places.json");

        return placeRepository.readFromJsonFile(resource);

    }

    public PlaceDTO store(PlaceDTO placeDTO) {
        Resource resource = resourceLoader.getResource("classpath:Places.json");
        return placeRepository.writeToJsonFile(placeDTO ,resource);
    }

    public PlaceDTO getById(Long id)
    {
        Resource resource = resourceLoader.getResource("classpath:Places.json");
        return placeRepository.searchDataById(id , resource);
    }


    public PlaceDTO update(Long id , PlaceDTO placeDTO){
        Resource resource = resourceLoader.getResource("classpath:Places.json");
        return placeRepository.UpdateById(id ,placeDTO,resource);
    }

    public void delete(Long id){
        Resource resource = resourceLoader.getResource("classpath:Places.json");
        placeRepository.deleteById(id ,resource);
    }
}
