package com.tradesim.controller;

import com.tradesim.model.Asset;
import com.tradesim.repository.AssetRepository;
import com.tradesim.service.CoinGeckoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets") // All endpoints start with /api/assets
@CrossOrigin(origins = "http://localhost:5173") // Allow React to talk to us
public class AssetController {

    private final AssetRepository assetRepository;
    private final CoinGeckoService coinGeckoService;

    public AssetController(AssetRepository assetRepository, CoinGeckoService coinGeckoService) {
        this.assetRepository = assetRepository;
        this.coinGeckoService = coinGeckoService;
    }

    // Endpoint: GET /api/assets
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    // Endpoint: POST /api/assets/refresh (To manually trigger an update)
    @PostMapping("/refresh")
    public String refreshData() {
        coinGeckoService.updateMarketPrices();
        return "Market data refresh initiated!";
    }
}