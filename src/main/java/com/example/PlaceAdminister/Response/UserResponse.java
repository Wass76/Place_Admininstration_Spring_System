package com.example.PlaceAdminister.Response;

import com.example.PlaceAdminister.Model_Entitiy.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String place;
    private Role role;
    private String imagePath;

}
