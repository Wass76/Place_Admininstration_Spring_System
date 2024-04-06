package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import com.example.PlaceAdminister.Repository.UserRepository;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//package com.example.PlaceAdminister.Service;
//
//import com.example.PlaceAdminister.DTO.ReservationDTO;
//import com.example.PlaceAdminister.DTO.TableDTO;
//import com.example.PlaceAdminister.DTO.UserDTO;
//import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
//import com.example.PlaceAdminister.Repository.UserRepository;
//import com.example.PlaceAdminister.Request.UserRequest;
//import lombok.RequiredArgsConstructor;
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//import static java.time.LocalTime.now;
//
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlaceRepository placeRepository;

    public UserResponse newUser(RegisterRequest request){
        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .place(placeRepository.getReferenceById(request.getPlace_id()))
//                .imagePath(fileName)
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .place(user.getPlace().getName())
                .role(user.getRole())
                .imagePath(user.getImagePath())
                .build();
    }


    public UserResponse editUser(RegisterRequest request , Long id){
//        var user = userRepository.findByEmail(request.getEmail());
        UserEntity user = userRepository.getReferenceById(id);
        if (user != null){
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole());
            user.setPlace(placeRepository.getReferenceById(request.getPlace_id()));
            userRepository.save(user);
        }
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .place(user.getPlace().getName())
                .build();
    }

    public ResponseEntity delete(Long id){
        UserEntity user = userRepository.getReferenceById(id);
        if(user != null){
            userRepository.deleteById(id);
        }
        return ResponseEntity.ok().body("delete Done successfully" + user.getEmail() + user.getFirstName()) ;
    }

}


