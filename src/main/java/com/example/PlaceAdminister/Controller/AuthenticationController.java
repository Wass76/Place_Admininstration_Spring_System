//package com.example.PlaceAdminister.Controller;
//
//import com.example.PlaceAdminister.DTO.UserDTO;
//import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
//import com.example.PlaceAdminister.Repository.UserRepository;
//import com.example.PlaceAdminister.Request.UserRequest;
//import com.example.PlaceAdminister.Service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@RequiredArgsConstructor
//public class AuthenticationController {
//    private final UserService userService;
//    private final JwtUtils jwtUtils;
//    private final String filePath="src/main/resources/Users.json";
//    private final AuthenticationManager authenticationManager;
//    private final UserRepository userRepository;
//
//    @PostMapping("register")
//    public UserDTO register(@RequestBody UserRequest userRequest){
//        return userService.register(userRequest);
//
//    }
//
//    @PostMapping("authenticate")
//    public ResponseEntity<String> authenticate(@RequestBody UserRequest request){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getPhoneNumber(),
//                        request.getPassword()
//                )
//        );
//        final UserDetails userDetails = userRepository.findByPhoneNumber(request.getPhoneNumber(), filePath);
//        final String jwt=jwtUtils.generateToken(userDetails);
//        return ResponseEntity.ok(jwt);
//
//    }
//}
