package com.tradesim.service;

import com.tradesim.model.Asset;
import com.tradesim.repository.AssetRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.ParameterizedTypeReference;

@Service
public class CoinGeckoService {

    private final AssetRepository assetRepository;
    private final WebClient webClient; // The tool to make HTTP requests

    public CoinGeckoService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
        this.webClient = WebClient.create("https://api.coingecko.com/api/v3");
    }

    // This method fetches data and saves it to our DB
    public void updateMarketPrices() {
        // 1. Call the API
        // Endpoint: /coins/markets?vs_currency=usd&ids=bitcoin,ethereum,solana
        try {
            List<Map<String, Object>> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/coins/markets")
                            .queryParam("vs_currency", "usd")
                            .queryParam("ids", "bitcoin,ethereum,solana,cardano,ripple")
                            .build())
                    .retrieve()
                    .bodyToFlux(new ParameterizedTypeReference<Map<String, Object>>() {}) // Convert JSON list to Java List
                    .collectList()
                    .block(); // Wait for the response (Synchronous for simplicity)

            // 2. Process and Save
            if (response != null) {
                for (Map<String, Object> coinData : response) {
                    Asset asset = new Asset(
                            (String) coinData.get("id"),       // symbol
                            (String) coinData.get("name"),     // name
                            ((Number) coinData.get("current_price")).doubleValue(), // price
                            (String) coinData.get("image")     // image
                    );
                    assetRepository.save(asset); // Update the DB
                }
                System.out.println("✅ Market Data Updated for " + response.size() + " coins.");
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to fetch prices: " + e.getMessage());
        }
    }
}