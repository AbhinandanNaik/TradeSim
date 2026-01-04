package com.tradesim.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PortfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;     // Who owns it?
    private String symbol;   // What do they own? (e.g., "bitcoin")
    private double quantity; // How much?

    public PortfolioItem(Long userId, String symbol, double quantity) {
        this.userId = userId;
        this.symbol = symbol;
        this.quantity = quantity;
    }
}