package com.reservation.api.controller;

import com.reservation.api.SecurityConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final SecurityConfig securityConfig;

    public AuthController(AuthenticationManager authManager, SecurityConfig securityConfig) {
        this.authManager = authManager;
        this.securityConfig = securityConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = securityConfig.generateToken(auth);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
