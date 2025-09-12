package com.reservation.api.repository;

import com.reservation.api.entity.ReservationHistoric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationHistoricRepository extends JpaRepository <ReservationHistoric, Long> {}
