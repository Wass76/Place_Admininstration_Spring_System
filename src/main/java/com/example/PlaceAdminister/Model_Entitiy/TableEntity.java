package com.example.PlaceAdminister.Model_Entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.sound.midi.Sequence;
import java.sql.Time;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
//@Table(name = "table")
public class TableEntity {

    @Id
    @SequenceGenerator(
            name = "table_id"
            ,sequenceName = "table_id"
            ,allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "table_id"
    )
    @JsonProperty("id")
    private Long id;

//    @ElementCollection
//    @JsonProperty("available_seats")
//    private List<Boolean> available_seats;

    @JsonProperty("status")
    private Integer status; // Full - Reserved - Available

    @JsonProperty("time_of_reservation")
    private Time time_of_reservation;

    @JsonProperty("room_id")
    private Long room_id;

}
