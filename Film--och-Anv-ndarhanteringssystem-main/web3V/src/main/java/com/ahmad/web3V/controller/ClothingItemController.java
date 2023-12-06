package com.ahmad.web3V.controller;

import com.ahmad.web3V.model.Product;
import com.ahmad.web3V.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ahmad.web3V.service.AuthenticationService;

import java.util.List;

@RestController
@RequestMapping(value = "/webshop/artiklar")
public class ClothingItemController {

    // Autowired instance of ClothingItemService for handling clothing item-related operations
    @Autowired
    private ProductService productService;

    // Autowired instance of AuthenticationService (consider renaming userService to avoid confusion)
    @Autowired
    private AuthenticationService userService;

    // Endpoint to get all clothing items
    @GetMapping(value = {"/"})
    public ResponseEntity<List<Product>> getAllClothingItems() {
        // Retrieve all clothing items from the ClothingItemService
        List<Product> result = productService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint to get a clothing item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getClothingItemById(@PathVariable Integer id) {
        // Retrieve a clothing item by its ID from the ClothingItemService
        Product result = productService.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint to create a new clothing item
    @PostMapping("/")
    public ResponseEntity<Product> createNewClothingItem(@RequestBody Product product) {
        // Save the new clothing item using the ClothingItemService
        Product result = productService.save(product);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Endpoint to update an existing clothing item by its ID
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateClothingItem(@PathVariable Integer id, @RequestBody Product product) {
        // Update the existing clothing item using the ClothingItemService
        Product updatedProduct = productService.update(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Endpoint to delete a clothing item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothingItem(@PathVariable Integer id) {
        // Delete the clothing item by its ID using the ClothingItemService
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
