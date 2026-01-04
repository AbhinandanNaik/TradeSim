package com.tradesim.repository;

import com.tradesim.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {
    // That's it!
    // We now have methods like .save(), .findAll(), .findById() for free.
}