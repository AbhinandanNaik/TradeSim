package com.tradesim.controller;

import com.tradesim.model.User;
import com.tradesim.repository.UserRepository;
import com.tradesim.service.TradeService;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
@CrossOrigin(origins = "http://localhost:5173")
public class TradeController {

    private final TradeService tradeService;
    private final UserRepository userRepository; // We need this to check balance

    public TradeController(TradeService tradeService, UserRepository userRepository) {
        this.tradeService = tradeService;
        this.userRepository = userRepository;
    }

    // 1. BUY Endpoint
    @PostMapping("/buy")
    public String buy(@RequestParam Long userId, @RequestParam String symbol, @RequestParam double quantity) {
        try {
            return tradeService.buyAsset(userId, symbol, quantity);
        } catch (RuntimeException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // 2. NEW: GET BALANCE Endpoint
    @GetMapping("/balance")
    public double getUserBalance(@RequestParam Long userId) {
        if (userId == null) {
            throw new RuntimeException("User ID cannot be null");
        }
        return userRepository.findById(userId)
                .map(User::getBalance)
                .orElse(0.0);
    }
}