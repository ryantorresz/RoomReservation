// src/main/java/com/reservation/api/controller/RoomController.java
package com.reservation.api.controller;

import com.reservation.api.entity.Room;
import com.reservation.api.service.RoomService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService service;
    public RoomController(RoomService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<Room>> list(){ return ResponseEntity.ok(service.listAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Room> get(@PathVariable Long id){ return ResponseEntity.ok(service.findById(id)); }

    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room r){ return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r)); }

    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody Room r){ return ResponseEntity.ok(service.update(id, r)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
