package com.example.PlaceAdminister.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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



}
//
//    @Autowired
//    private UserEntity user;
//    @Autowired
//    private UserRepository userRepository;
//
//    private final PasswordEncoder encoder;
//
//    private String userFilePath = "src/main/resources/Users.json";
//
//
//    public List<UserDTO> getAllTables(){
//        return userRepository.readFromJsonFile(userFilePath);
//    }
//
//    public UserDTO store(UserDTO userDTO){
//        UserDTO newUser=userDTO;
//        newUser.setPassword(encoder.encode(newUser.getPassword()));
//        return userRepository.writeToJsonFile(newUser ,this.userFilePath);
//    }
//
//    public UserDTO show(Long id)
//    {
//        return userRepository.searchDataById(id , this.userFilePath);
//    }
//
//
//
//    public UserDTO update(Long id , UserDTO userDTO){
//        return userRepository.UpdateById(id ,userDTO,this.userFilePath);
//    }
//
//    public void delete(Long id){
//        userRepository.deleteById(id,this.userFilePath);
//    }
//
//    public UserDTO login(UserRequest userRequest){
//        List<UserDTO> users=userRepository.readFromJsonFile(this.userFilePath);
//        for(int i=0;i<users.size();i++){
//            if(users.get(i).getPhoneNumber()== user.getPhoneNumber()){
//                if(users.get(i).getPassword()== user.getPassword())
//                    return users.get(i);
//                else new UserDTO("Wrong Passsword");
//            }
//        }
//        return new UserDTO("Not Found");
//    }
//
//    public UserDTO register(UserRequest userRequest){
//        List<UserDTO> users=userRepository.readFromJsonFile(this.userFilePath);
//        for(int i=0;i<users.size();i++){
//            if(users.get(i).getPhoneNumber()== user.getPhoneNumber()){
//                    return new UserDTO("already exist");
//            }
//        }
//        UserDTO user=store(new UserDTO(userRequest));
//        return user;
//    }
//
//}
//
