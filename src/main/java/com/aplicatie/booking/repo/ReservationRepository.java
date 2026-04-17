package com.aplicatie.booking.repo;

import com.aplicatie.booking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.room.id = :roomId " +
            "AND r.reservationDate = :date " +
            "AND r.startTime < :endTime AND r.endTime > :startTime")
    boolean isRoomOccupied(@Param("roomId") Integer roomId,
                           @Param("date") LocalDate date,
                           @Param("startTime") LocalTime startTime,
                           @Param("endTime") LocalTime endTime);
    List<Reservation> findByUserId(Integer userId);

    List<Reservation> findByRoomIdAndReservationDate(Integer roomId, LocalDate date);
}

