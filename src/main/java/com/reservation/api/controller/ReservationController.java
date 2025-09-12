// src/main/java/com/reservation/api/controller/ReservationController.java
package com.reservation.api.controller;

import com.reservation.api.entity.Reservation;
import com.reservation.api.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    private final ReservationService service;
    public ReservationController(ReservationService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<Reservation>> list(){ return ResponseEntity.ok(service.listAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> get(@PathVariable Long id){ return ResponseEntity.ok(service.findById(id)); }

    @PostMapping
    public ResponseEntity<Reservation> create(
            @RequestParam Long roomId,
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        Reservation created = service.create(roomId, userId, start, end);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Reservation> cancel(@PathVariable Long id){ return ResponseEntity.ok(service.cancel(id)); }
}
