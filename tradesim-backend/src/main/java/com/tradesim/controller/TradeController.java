package com.tradesim.controller;

import com.tradesim.service.TradeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
@CrossOrigin(origins = "http://localhost:5173")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    // Endpoint: POST /api/trade/buy?userId=1&symbol=bitcoin&quantity=0.5
    @PostMapping("/buy")
    public String buy(@RequestParam Long userId, @RequestParam String symbol, @RequestParam double quantity) {
        try {
            return tradeService.buyAsset(userId, symbol, quantity);
        } catch (RuntimeException e) {
            return "ERROR: " + e.getMessage();
        }
    }
}