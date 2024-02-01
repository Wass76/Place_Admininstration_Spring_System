package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


public class RoomEntity {

    @JsonProperty("id")
    private int id;
    @JsonProperty("max_num_of_chairs")
    private int max_num_of_chairs;
    @JsonProperty("categoriesId")
    private Set<Integer> categoriesId = new HashSet<>();
    @JsonProperty("status")
    private boolean status;
    @JsonProperty("time_0f_reservation")
    private Time time_0f_reservation;

    public RoomEntity(){}
    public RoomEntity(int id, int max_num_of_chairs, boolean status, Time time_f_reservation) {
        this.id=id;
        this.max_num_of_chairs = max_num_of_chairs;
        this.status = status;
        this.time_0f_reservation = time_f_reservation;
    }

    public int getMax_num_of_chairs() {
        return max_num_of_chairs;
    }

    public void setMax_num_of_chairs(int max_num_of_chairs) {
        this.max_num_of_chairs = max_num_of_chairs;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Time getTime_0f_reservation() {
        return time_0f_reservation;
    }

    public void setTime_0f_reservation(Time time_f_reservation) {
        this.time_0f_reservation = time_f_reservation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public Set<Integer> getCategories() {
        return categoriesId;
    }

    public void setCategories(Set<Integer> categories) {
        this.categoriesId = categoriesId;
    }
    public void addCategory(int roomCategoryEntity){
        this.categoriesId.add(roomCategoryEntity);
    }
}
