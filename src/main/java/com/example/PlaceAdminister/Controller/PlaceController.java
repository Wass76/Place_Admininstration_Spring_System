package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.DTO.PlaceDTO;
import com.example.PlaceAdminister.Exception.ApiRequestException;
import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Request.PlaceRequest;
import com.example.PlaceAdminister.Service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/places")
//@PreAuthorize("hasRole('ADMIN')")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping("allplaces")
//    @PreAuthorize("hasAnyAuthority('')")
    public ResponseEntity getAllPlacess() {
        List<PlaceEntity> placeList  = placeService.getAllPlaces();
        if(placeList == null || placeList.isEmpty() ){
            return ResponseEntity.status(200).body("there is no Place yet");
        }
        return ResponseEntity.ok(placeList);
    }

    @PostMapping("newplace")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity addPlace(
//            @RequestBody PlaceRequest placeRequest
            @RequestParam String name,
            @RequestParam String locations
//            @RequestParam MultipartFile file
            ) throws IOException {
        PlaceRequest placeRequest = PlaceRequest.builder()
                .name(name)
                .locations(locations)
//                .file(file)
                .build();
        if(placeRequest.getLocations() == null || placeRequest.getLocations().isEmpty() || placeRequest.getName() == null){
            throw new ApiRequestException("validate your data please");
//            return ResponseEntity.badRequest().body("validate your data please");
        }
        PlaceDTO placeDTO = new PlaceDTO(placeRequest);
        PlaceEntity place = placeService.store(placeDTO);
        if(place == null){
            throw new ApiRequestException("please try again");
//            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
        }
        return ResponseEntity.ok(place);
    }


    @GetMapping("show/{id}")
    public ResponseEntity show(@PathVariable("id") Long id){
        try {
            if(id == null || id<=0){
                throw new ApiRequestException("Invalid Id");
//                return ResponseEntity.badRequest().body("Invalid Id");
            }

            PlaceEntity placeDTO = placeService.getById(id);
            if (placeDTO == null) {
                throw new ApiRequestException("can't find this item");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
            }
            return ResponseEntity.ok(placeDTO);
        }catch (Exception e){
            throw new ApiRequestException("An error occurred while updating the place, maybe place_id is not correct");
//            return ResponseEntity.internalServerError().body("An error occurred while updating the place, maybe place_id is not correct");
        }

    }

    @PutMapping ("edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity edit(@PathVariable("id") Long id ,@RequestBody PlaceRequest request){
        try {
            if(id == null || id<=0)
           {
               throw new ApiRequestException("Invalid Id");
//              return  ResponseEntity.badRequest().body("Invalid Id");
            }
            if(request.getName() == null || request.getLocations() == null || request.getLocations().isEmpty()){
                throw new ApiRequestException("validate your data please");
//                return ResponseEntity.badRequest().body("validate your data please");
             }
             PlaceDTO placeDTO = new PlaceDTO(request);

            PlaceEntity place = placeService.getById(id);
            if(place == null){
                throw new ApiRequestException("can't find this item");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
            }
           PlaceEntity newPlace =  placeService.update(id ,placeDTO);
            if(newPlace == null){
                throw new ApiRequestException("please try again");
//                return ResponseEntity.status(HttpStatus.RESET_CONTENT).body("please try again");
            }
            return ResponseEntity.ok(newPlace);
        }catch (Exception e){
            throw new ApiRequestException("An error occurred while updating the place, maybe place_id is not correct");
//            return ResponseEntity.internalServerError().body("An error occurred while updating the place, maybe place_id is not correct");

        }
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(id == null || id<=0){
            throw new ApiRequestException("Invalid Id");
//            return ResponseEntity.badRequest().body("Invalid Id");
        }

        PlaceEntity placeDTO = placeService.getById(id);
        if(placeDTO == null){
            throw new ApiRequestException("can't find this item");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find this item");
        }
        placeService.delete(id);
        return ResponseEntity.ok("delete done successfully");
    }
}
