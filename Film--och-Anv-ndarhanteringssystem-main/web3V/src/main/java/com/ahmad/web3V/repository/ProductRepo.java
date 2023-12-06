package com.ahmad.web3V.repository;

import com.ahmad.web3V.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    // Custom query method to find a clothing item by its name
    Product findByName(String name);
}
