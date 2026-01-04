package com.tradesim.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users") // "User" is a reserved word in SQL, so we name table "users"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private double balance; // USD Balance (e.g., 10000.00)

    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }
}