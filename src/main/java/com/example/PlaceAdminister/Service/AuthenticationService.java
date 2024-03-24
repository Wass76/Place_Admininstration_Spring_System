package com.example.PlaceAdminister.Service;

import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import com.example.PlaceAdminister.Repository.PlaceRepository;
import com.example.PlaceAdminister.Repository.UserRepository;
import com.example.PlaceAdminister.Request.AuthenticationRequest;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Response.AuthenticationResponse;
import com.example.PlaceAdminister.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final PlaceRepository placeRepository;
    private final AuthenticationManager authenticationManager;
    private final Logger logger;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
//                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .place(placeRepository.getReferenceById(request.getPlace_id()))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
