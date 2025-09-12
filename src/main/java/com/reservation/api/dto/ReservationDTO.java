package com.reservation.api.dto;

import java.time.LocalDateTime;

public record ReservationDTO (Long id, Long roomId, Long userId, LocalDateTime startTime, LocalDateTime endTime, String status){
}
