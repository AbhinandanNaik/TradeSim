package com.tradesim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 1. Tells Spring: "Make a table called 'Asset' out of this class"
@Data   // 2. Lombok: Generates Getters, Setters, and toString() automatically
@AllArgsConstructor // 3. Generates a constructor with all arguments
@NoArgsConstructor  // 4. Generates an empty constructor (required by JPA)
public class Asset {

    @Id // 5. The Primary Key (e.g., "bitcoin")
    private String symbol;

    private String name;        // e.g., "Bitcoin"
    private double currentPrice;// e.g., 50000.00
    private String imageUrl;    // URL to the coin's logo
}