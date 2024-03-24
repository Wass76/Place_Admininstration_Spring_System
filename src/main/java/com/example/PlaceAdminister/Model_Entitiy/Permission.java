package com.example.PlaceAdminister.Model_Entitiy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
//    ADMIN_READ("admin:read"),
//    ADMIN_CREATE("admin:create"),
//    ADMIN_UPDATE("admin:update"),
//    ADMIN_DELETE("admin:delete"),
//
//    MANAGER_READ("management:read"),
//    MANAGER_CREATE("management:create"),
//    MANAGER_UPDATE("management:update"),
//    MANAGER_DELETE("management:delete"),

    CREATE_PLACE("place:create"),
    UPDATE_PLACE("place:update"),
    DELETE_PLACE("places:delete"),

    CREATE_ROOM_CATEGORY("roomCategory:create"),
    UPDATE_ROOM_CATEGORY("roomCategory:update"),
    DELETE_ROOM_CATEGORY("roomCategory:delete"),

    CREATE_ROOM("room:create"),
    UPDATE_ROOM("room:update"),
    DELETE_ROOM("room:delete"),

    CREATE_TABLE_CATEGORY("tableCategory:create"),
    UPDATE_TABLE_CATEGORY("tableCategory:update"),
    DELETE_TABLE_CATEGORY("tableCategory:delete"),

    CREATE_TABLE("table:create"),
    UPDATE_TABLE("table:update"),
    DELETE_TABLE("table:delete"),

    READ_MANAGER("manager:read"),
    CREATE_MANAGER("manager:create"),
    UPDATE_MANAGER("manager:update"),
    DELETE_MANAGER("manager:delete"),

    READ_SUPERVISOR("supervisor:read"),
    CREATE_SUPERVISOR("supervisor:create"),
    UPDATE_SUPERVISOR("supervisor:update"),
    DELETE_SUPERVISOR("supervisor:delete");



    @Getter
    private final String permission;

}
