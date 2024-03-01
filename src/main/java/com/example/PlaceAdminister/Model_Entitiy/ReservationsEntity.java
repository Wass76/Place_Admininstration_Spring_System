//package com.example.PlaceAdminister.Model_Entitiy;
//
//import com.example.PlaceAdminister.Request.MakeReservationRequest;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class ReservationsEntity {
//    @Id
//    @SequenceGenerator(
//            name = "reservation_id"
//            ,sequenceName = "reservation_id"
//            ,allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "reservation_id"
//    )
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "type")
//    private Integer type; // 1= room , 2 = table , 3 = seat
//
//    @OneToOne(mappedBy = "reservation")
//    @Column(name = "room_id")
//    private RoomEntity room_id;
//
//    @OneToOne(mappedBy = "reservation")
//    @Column(name = "table_id")
//    private TableEntity table_id;
//
//    @Column(name = "user_id")
//    private Long user_id;
//
//    @Column(name = "num_of_seats")
//    private Integer num_of_seats;
//
//    @Column(name = "time")
//    private Time time;
//
//    @Column( name = "period_of_reservations")
//    private Integer period_of_reservations;
//
////    public ReservationsEntity(Long id,Integer type,Long room_id,Long table_id,Long seat_id,LocalDateTime time,Integer period_of_reservations){
////       this.id = id;
////        this.type  = type;
////        this.room_id = room_id;
////        this.table_id = table_id;
////        this. seat_id = seat_id;
////        this. time = time;
////        this. period_of_reservations = period_of_reservations;
////    }
//
//
//}
