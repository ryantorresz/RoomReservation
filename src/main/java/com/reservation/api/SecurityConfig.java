package com.reservation.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Key;
import io.jsonwebtoken.security.Keys;

@Configuration
public class SecurityConfig {

    private final Key jwtKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {

        JWTAuthenticationFilter jwtFilter = new JWTAuthenticationFilter(userDetailsService, jwtKey);

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/rooms/**", "/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/reservations/**").authenticated()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
