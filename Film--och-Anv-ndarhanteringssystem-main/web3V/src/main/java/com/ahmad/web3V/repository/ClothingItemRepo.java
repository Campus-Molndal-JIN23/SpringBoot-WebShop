package com.ahmad.web3V.repository;

import com.ahmad.web3V.model.ClothingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingItemRepo extends JpaRepository<ClothingItem, Integer> {
    // Custom query method to find a clothing item by its name
    ClothingItem findByName(String name);
}
