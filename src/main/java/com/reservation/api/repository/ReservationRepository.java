package com.reservation.api.repository;

import com.reservation.api.entity.Reservation;
import com.reservation.api.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoomAndEndTimeGreaterThanAndStartTimeLessThanAndStatus(Room room, LocalDateTime end, ReservationStatus status);
}
