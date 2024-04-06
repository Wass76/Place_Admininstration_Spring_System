package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.Model_Entitiy.Role;
import com.example.PlaceAdminister.Request.AuthenticationRequest;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Response.AuthenticationResponse;
import com.example.PlaceAdminister.Service.AuthenticationService;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

import static com.example.PlaceAdminister.Model_Entitiy.Role.USER;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@MultipartConfig
public class AuthenticationContoller {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest request
//            @RequestParam("firstName") String firstName,
//            @RequestParam("lastName") String lastName,
//            @RequestParam("email") String email,
//            @RequestParam("password") String password
//            @RequestParam("file")MultipartFile file
//            MultipartRequest request
    ) throws IOException {
//        if(request.getRole() == null)
//            request.setRole(USER);
//        if(request.getFile().equals(null
//        ))
//            return ResponseEntity.badRequest().body("You Should add image");
//        if(role.equals(null)){
//            role = "USER";
//        }
//        if(file.equals(null))
//            return ResponseEntity.badRequest().body("You Should add image");
//        RegisterRequest request = RegisterRequest.builder()
//                .firstName(firstName)
//                .lastName(lastName)
//                .email(email)
//                .password(password)
//                .role(USER)
////                .place_id(place_id)
////                .file(file)
//                .build();
//        System.out.println(password);
        request.setRole(USER);
       return ResponseEntity.ok(authenticationService.register(request));
    }
//    @GetMapping(value = "/get-photo/{id}" ,produces = "image/png")
//    public ResponseEntity photo(@PathVariable Long id) throws IOException {
////        Long id = 3;
//        var user = authenticationService.getImage(id);
////        user.getBytes();
//        return ResponseEntity.ok(user.getBytes());
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
       return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthenticationResponse> logout(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
