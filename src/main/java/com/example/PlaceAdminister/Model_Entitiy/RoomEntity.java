package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RoomEntity {

    @Id
    @SequenceGenerator(
            name = "Room_id_sequence",
            sequenceName = "Room_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Room_id_sequence"
    )
    @JsonProperty("id")
    private Long id;
    @JsonProperty("max_num_of_chairs")
    private int max_num_of_chairs;
    @JsonProperty("categoriesId")
    private Set<Long> categoriesId = new HashSet<>();
    @JsonProperty("status")
    private String status;
    @JsonProperty("time_0f_reservation")

    private Time time_0f_reservation;

    public RoomEntity(){}
    public RoomEntity(Long id, int max_num_of_chairs, String status, Time time_f_reservation) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Time getTime_0f_reservation() {
        return time_0f_reservation;
    }

    public void setTime_0f_reservation(Time time_f_reservation) {
        this.time_0f_reservation = time_f_reservation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public Set<Long> getCategories() {
        return categoriesId;
    }

    public void setCategories(Set<Long> categories) {
        this.categoriesId = categoriesId;
    }
    public void addCategory(Long roomCategoryEntity){
        this.categoriesId.add(roomCategoryEntity);
    }
}
