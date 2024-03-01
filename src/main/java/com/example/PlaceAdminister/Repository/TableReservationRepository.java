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
public interface TableReservationRepository extends JpaRepository<TableReservation,Long> {


    @Query("SELECT t FROM TableReservation t WHERE t.place.id = :placeId")
    List<TableReservation> findByPlaceId(@Param("placeId") Long placeId);

    @Query("SELECT t FROM TableReservation t WHERE t.place.id = :placeId AND (t.time = :time OR t.time BETWEEN :timeStart AND :timeEnd)")
    List<TableReservation> getTimeReservations(@Param("placeId") Long placeId,
                                              @Param("time") LocalDateTime time,
                                              @Param("timeStart") LocalDateTime timeStart,
                                              @Param("timeEnd") LocalDateTime timeEnd);

    List<TableReservation> getByTableId(Long id);
    List<TableReservation> getByTime(Time time);

//    List<TableReservation> getByPeriodOfReservations(Integer period);
}