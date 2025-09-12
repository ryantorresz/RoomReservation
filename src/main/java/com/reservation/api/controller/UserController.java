// src/main/java/com/reservation/api/controller/UserController.java
package com.reservation.api.controller;

import com.reservation.api.entity.User;
import com.reservation.api.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<User>> list(){ return ResponseEntity.ok(service.listAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){ return ResponseEntity.ok(service.findById(id)); }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User u){ return ResponseEntity.status(HttpStatus.CREATED).body(service.create(u)); }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User u){ return ResponseEntity.ok(service.update(id, u)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
