package com.ahmad.web3V.controller;

import com.ahmad.web3V.model.ClothingItem;
import com.ahmad.web3V.service.ClothingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ahmad.web3V.model.ApplicationUser;
import com.ahmad.web3V.model.LoginResponseDTO;
import com.ahmad.web3V.model.RegistrationDTO;
import com.ahmad.web3V.service.AuthenticationService;

import java.util.List;

@RestController
@RequestMapping(value = "/webshop/artiklar")
public class ClothingItemController {

    // Autowired instance of ClothingItemService for handling clothing item-related operations
    @Autowired
    private ClothingItemService clothingItemService;

    // Autowired instance of AuthenticationService (consider renaming userService to avoid confusion)
    @Autowired
    private AuthenticationService userService;

    // Endpoint to get all clothing items
    @GetMapping(value = {"/"})
    public ResponseEntity<List<ClothingItem>> getAllClothingItems() {
        // Retrieve all clothing items from the ClothingItemService
        List<ClothingItem> result = clothingItemService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint to get a clothing item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ClothingItem> getClothingItemById(@PathVariable Integer id) {
        // Retrieve a clothing item by its ID from the ClothingItemService
        ClothingItem result = clothingItemService.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint to create a new clothing item
    @PostMapping("/")
    public ResponseEntity<ClothingItem> createNewClothingItem(@RequestBody ClothingItem clothingItem) {
        // Save the new clothing item using the ClothingItemService
        ClothingItem result = clothingItemService.save(clothingItem);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Endpoint to update an existing clothing item by its ID
    @PatchMapping("/{id}")
    public ResponseEntity<ClothingItem> updateClothingItem(@PathVariable Integer id, @RequestBody ClothingItem clothingItem) {
        // Update the existing clothing item using the ClothingItemService
        ClothingItem updatedClothingItem = clothingItemService.update(id, clothingItem);
        return new ResponseEntity<>(updatedClothingItem, HttpStatus.OK);
    }

    // Endpoint to delete a clothing item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothingItem(@PathVariable Integer id) {
        // Delete the clothing item by its ID using the ClothingItemService
        clothingItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
