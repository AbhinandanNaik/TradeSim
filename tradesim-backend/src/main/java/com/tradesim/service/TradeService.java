package com.tradesim.service;

import com.tradesim.model.Asset;
import com.tradesim.model.PortfolioItem;
import com.tradesim.model.User;
import com.tradesim.repository.AssetRepository;
import com.tradesim.repository.PortfolioItemRepository;
import com.tradesim.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public TradeService(UserRepository userRepository, AssetRepository assetRepository, PortfolioItemRepository portfolioItemRepository) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    @Transactional // <--- IMPORTANT: This ensures data integrity. All or nothing.
    public String buyAsset(Long userId, String symbol, double quantity) {
        // 1. Get the User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Get the Asset Price
        Asset asset = assetRepository.findById(symbol)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        // 3. Calculate Cost
        double totalCost = asset.getCurrentPrice() * quantity;

        // 4. Check Balance
        if (user.getBalance() < totalCost) {
            throw new RuntimeException("Insufficient funds! Needed: $" + totalCost + ", Has: $" + user.getBalance());
        }

        // 5. Deduct Money
        user.setBalance(user.getBalance() - totalCost);
        userRepository.save(user);

        // 6. Add Asset to Portfolio
        PortfolioItem item = portfolioItemRepository.findByUserIdAndSymbol(userId, symbol);
        if (item == null) {
            // New holding
            item = new PortfolioItem(userId, symbol, quantity);
        } else {
            // Update existing holding
            item.setQuantity(item.getQuantity() + quantity);
        }
        portfolioItemRepository.save(item);

        return "SUCCESS: Bought " + quantity + " " + symbol + " for $" + totalCost;
    }
}