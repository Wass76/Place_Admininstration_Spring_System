package com.example.PlaceAdminister.Repository;

import com.example.PlaceAdminister.Model_Entitiy.PlaceEntity;
import com.example.PlaceAdminister.Model_Entitiy.RoomReservation;
import com.example.PlaceAdminister.Model_Entitiy.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Component
public interface RoomReservationRepository  extends JpaRepository<RoomReservation , Long> {

    @Query("SELECT r FROM RoomReservation r WHERE r.place.id = :placeId")
    List<RoomReservation> findByPlaceId(@Param("placeId") Long placeId);

    @Query("SELECT r FROM RoomReservation r WHERE r.place.id = :placeId AND (r.time = :time OR r.time BETWEEN :timeStart AND :timeEnd)")
    List<RoomReservation> getTimeReservations(@Param("placeId") Long placeId,
                                              @Param("time") LocalDateTime time,
                                              @Param("timeStart") LocalDateTime timeStart,
                                              @Param("timeEnd") LocalDateTime timeEnd);

    List<RoomReservation> getByRoomId(Long id);
    List<RoomReservation> getByTime(Time time);
//    List<RoomReservation> getByPeriodOfReservations(Integer period);

//    List<RoomReservation> findByTwoColumns(Time time , Long room_id);
}
