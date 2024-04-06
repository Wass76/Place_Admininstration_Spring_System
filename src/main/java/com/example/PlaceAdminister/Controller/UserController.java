package com.example.PlaceAdminister.Controller;

import com.example.PlaceAdminister.Model_Entitiy.Role;
import com.example.PlaceAdminister.Request.AuthenticationRequest;
import com.example.PlaceAdminister.Request.RegisterRequest;
import com.example.PlaceAdminister.Request.UserRequest;
import com.example.PlaceAdminister.Response.AuthenticationResponse;
import com.example.PlaceAdminister.Response.UserResponse;
import com.example.PlaceAdminister.Service.AuthenticationService;
import com.example.PlaceAdminister.Service.UserService;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

import static com.example.PlaceAdminister.Model_Entitiy.Role.USER;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@MultipartConfig
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping(value = "/new-user")
    public ResponseEntity addAdmin(
//            @RequestBody RegisterRequest request,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("role")Role role,
            @RequestParam("place_id") Long place_id
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
        RegisterRequest request = RegisterRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(role)
                .place_id(place_id)
//                .file(file)
                .build();
        System.out.println(password);
        return ResponseEntity.ok(userService.newUser(request));
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<UserResponse> edit(
            @RequestBody RegisterRequest request,
            @PathVariable Long id
    ){

        return ResponseEntity.ok(userService.editUser(request,id));
    }

}
