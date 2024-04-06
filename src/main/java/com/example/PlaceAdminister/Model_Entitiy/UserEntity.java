package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@Builder
public class UserEntity implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_id",
            sequenceName = "user_id",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Replace byte[] with String to store the image path
    private String imagePath;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
