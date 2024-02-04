package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class UserEntity {

    @JsonProperty("id")
    @Id
    private Long id;
    @JsonProperty("UserName")
    private String UserName;
    @JsonProperty("role")
    private String role;
    @JsonProperty("phoneNumber")
    private Long phoneNumber;

    @JsonProperty("password")
    private String password;

    public UserEntity(String userName, String role, Long phoneNumber,String password) {
        UserName = userName;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.password=password;
    }
}
