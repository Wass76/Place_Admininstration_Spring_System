package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class RoomEntity {

    @JsonProperty("id")
    @Id
    private Long id;
    @JsonProperty("max_num_of_chairs")
    private int max_num_of_chairs;
    @JsonProperty("categoriesId")
    private Set<Long> categoriesId = new HashSet<>();
    @JsonProperty("tableIds")
    private Set<Long> tablesIds = new HashSet<>();
    @JsonProperty("status")
    private boolean status;
    @JsonProperty("time_0f_reservation")
    private Date time_0f_reservation;
    public RoomEntity(int max_num_of_chairs, Set<Long> categoriesId, Set<Long> tablesIds, boolean status, Date time_0f_reservation) {
        this.max_num_of_chairs = max_num_of_chairs;
        this.categoriesId = categoriesId;
        this.tablesIds = tablesIds;
        this.status = status;
        this.time_0f_reservation = time_0f_reservation;
    }
    public void addCategory(java.util.List<Long> roomCategoryIds){
        this.categoriesId.addAll(roomCategoryIds);
    }
    public void addTables(java.util.List<Long> tableIds){
        if(tableIds==null) return;
        this.tablesIds.addAll(tableIds);
    }



}
