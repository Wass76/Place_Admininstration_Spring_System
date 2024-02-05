//package com.example.PlaceAdminister.Controller;
//
//import com.example.PlaceAdminister.DTO.TableDTO;
//import com.example.PlaceAdminister.DTO.UserDTO;
//import com.example.PlaceAdminister.Request.TableRequest;
//import com.example.PlaceAdminister.Request.UserRequest;
//import com.example.PlaceAdminister.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.Time;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//@RestController
//@RequestMapping("api/v1/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//
//    @GetMapping("/AllUsers")
//    public List<UserDTO> index(){
//        return userService.getAllTables();
//    }
//
//    @PostMapping("/register")
//    public UserDTO create(@RequestBody UserRequest request)
//    {
//        UserDTO userDTO = new UserDTO(request);
//        return userService.register(request);
//    }
//
//    @GetMapping("{id}")
//    public UserDTO show(@PathVariable("id") Long id){
//        return userService.show(id);
//    }
//
//
//    @PutMapping("update/{id}")
//    public UserDTO edit(@PathVariable("id") Long id ,@RequestBody UserRequest request){
//        UserDTO userDTO = new UserDTO(request);
//        return userService.update(id ,userDTO);
//    }
//
//    @DeleteMapping("delete/{id}")
//    public void delete(@PathVariable("id") Long id ){
//        userService.delete(id);
//    }
//
//
//    @GetMapping("login")
//    public UserDTO login(@RequestBody UserRequest request){
//        return userService.login(request);
//
//    }
//}
