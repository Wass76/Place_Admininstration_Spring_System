package com.example.PlaceAdminister.Model_Entitiy;

import com.example.PlaceAdminister.DTO.RoomReservationDTO;
import com.example.PlaceAdminister.DTO.TableReservationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}) // Add these properties
public class TableReservation {
    @Id
    @SequenceGenerator(
            name = "tReservation_id"
            ,sequenceName = "tReservation_id"
            ,allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tReservation_id"
    )
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @Column(name = "status")
    private Integer status = 1; // 1 = Delayed (not now) - 2 = occupied (user take it) - 3 = waiting (time is now but user doesn't take it yet)

    @Column(name = "number_of_seats")
    private Integer number_of_seats;

    @Column(name = "time")
    private LocalDateTime time;

    @Column( name = "period_of_reservations")
    private Integer period_of_reservations;


    public TableReservation(TableReservationDTO reservationDTO) {
        this.number_of_seats = reservationDTO.getNumber_of_seats();
        this.time = reservationDTO.getTime();
        this.period_of_reservations = reservationDTO.getPeriod_of_reservations();
    }
}
