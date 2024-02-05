//package com.example.PlaceAdminister.Model_Entitiy;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Component
//public class UserEntity implements UserDetails {
//
//    @JsonProperty("id")
//    @Id
//    private Long id;
//    @JsonProperty("UserName")
//    private String userName;
//    @JsonProperty("role")
//    private String role;
//    @JsonProperty("phoneNumber")
//    private Long phoneNumber;
//
//    @JsonProperty("password")
//    private String password;
//
//    public UserEntity(String userName, String role, Long phoneNumber,String password) {
//        this.userName = userName;
//        this.role = role;
//        this.phoneNumber = phoneNumber;
//        this.password=password;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
