package com.reservation.api.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users") // evita conflito com palavras reservadas do banco
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String fullName;

    // Construtores
    public User() { }

    public User(String username, String fullName) {
        this.username = username;
        this.fullName = fullName;
    }

    // Getters e setters
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
