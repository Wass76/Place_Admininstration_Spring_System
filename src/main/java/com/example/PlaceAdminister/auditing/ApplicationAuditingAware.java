package com.example.PlaceAdminister.auditing;

import com.example.PlaceAdminister.Model_Entitiy.UserEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditingAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if(authentication == null ||
                ! authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }
        UserEntity userPrincipal = (UserEntity) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());
    }

}
