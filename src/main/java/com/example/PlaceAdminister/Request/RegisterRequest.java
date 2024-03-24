package com.example.PlaceAdminister.Request;

import com.example.PlaceAdminister.Model_Entitiy.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private String email;
//    private String username;

    private String password;
    private Role role;
    private Long place_id;
}
