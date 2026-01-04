package com.tradesim.repository;

import com.tradesim.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
    // Custom query: Find a specific coin in a specific user's wallet
    PortfolioItem findByUserIdAndSymbol(Long userId, String symbol);
}