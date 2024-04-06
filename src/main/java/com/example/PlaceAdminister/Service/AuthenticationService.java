package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import com.example.PlaceAdminister.Repository.UserRepository;
import com.example.PlaceAdminister.Request.AuthenticationRequest;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Response.AuthenticationResponse;
import com.example.PlaceAdminister.Response.UserResponse;
import com.example.PlaceAdminister.config.JwtService;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.StringUtils;



import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@MultipartConfig
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final PlaceRepository placeRepository;
    private final AuthenticationManager authenticationManager;
    private final Logger logger;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) throws IOException {
//        String fileName = storeImage(request.getFile()); // Call method to store image

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
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .place(user.getPlace().getName())
                .role(user.getRole())
//                .imagePath(user.getImagePath())
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .place(user.getPlace().getName())
                .role(user.getRole())
                .build();
    }
}
