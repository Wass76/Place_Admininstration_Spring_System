package com.example.PlaceAdminister.Model_Entitiy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.PlaceAdminister.Model_Entitiy.Permission.*;

@RequiredArgsConstructor
public enum Role {

        USER(Collections.emptySet()),
        ADMIN(

                Set.of(
                        CREATE_PLACE,
                        DELETE_PLACE,
                        UPDATE_PLACE,

                        CREATE_ROOM_CATEGORY,
                        UPDATE_ROOM_CATEGORY,
                        DELETE_ROOM_CATEGORY,

                        CREATE_ROOM,
                        UPDATE_ROOM,
                        DELETE_ROOM,

                        CREATE_TABLE_CATEGORY,
                        UPDATE_TABLE_CATEGORY,
                        DELETE_TABLE_CATEGORY,

                        CREATE_TABLE,
                        UPDATE_TABLE,
                        DELETE_TABLE,

                        READ_MANAGER,
                        CREATE_MANAGER,
                        UPDATE_MANAGER,
                        DELETE_MANAGER,

                        READ_SUPERVISOR,
                        CREATE_SUPERVISOR,
                        UPDATE_SUPERVISOR,
                        DELETE_SUPERVISOR
                )
        ),
        MANAGER(
                Set.of(
                        UPDATE_PLACE,

                        CREATE_ROOM_CATEGORY,
                        UPDATE_ROOM_CATEGORY,
                        DELETE_ROOM_CATEGORY,

                        CREATE_ROOM,
                        UPDATE_ROOM,
                        DELETE_ROOM,

                        CREATE_TABLE_CATEGORY,
                        UPDATE_TABLE_CATEGORY,
                        DELETE_TABLE_CATEGORY,

                        CREATE_TABLE,
                        UPDATE_TABLE,
                        DELETE_TABLE,

                        UPDATE_MANAGER,

                        READ_SUPERVISOR,
                        CREATE_SUPERVISOR,
                        UPDATE_SUPERVISOR,
                        DELETE_SUPERVISOR
                )
        ),
    SUPERVISOR(
            Set.of(
                    CREATE_ROOM_CATEGORY,
                    UPDATE_ROOM_CATEGORY,
                    DELETE_ROOM_CATEGORY,

                    CREATE_ROOM,
                    UPDATE_ROOM,
                    DELETE_ROOM,

                    CREATE_TABLE_CATEGORY,
                    UPDATE_TABLE_CATEGORY,
                    DELETE_TABLE_CATEGORY,

                    CREATE_TABLE,
                    UPDATE_TABLE,
                    DELETE_TABLE,

                    UPDATE_SUPERVISOR
            )
    );

        @Getter
        private final Set<Permission> permissions;

        public List<SimpleGrantedAuthority> getAuthorities(){
            var authorities=   getPermissions()
                    .stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                    .collect(Collectors.toList());

            authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

            return authorities;
        }
    }
