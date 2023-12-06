package com.ahmad.web3V.service;

import com.ahmad.web3V.error.ConflictException;
import com.ahmad.web3V.error.NotFoundException;
import com.ahmad.web3V.model.ClothingItem;
import com.ahmad.web3V.model.Film;
import com.ahmad.web3V.repository.ClothingItemRepo;
import com.ahmad.web3V.repository.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ClothingItemService {

    // Autowired instance of ClothingItemRepo for clothing item-related operations
    @Autowired
    private ClothingItemRepo clothingItemRepository;

    // Method to retrieve all clothing items
    public List<ClothingItem> findAll() {
        return clothingItemRepository.findAll();
    }

    // Method to retrieve a clothing item by its ID
    public ClothingItem getById(Integer id) {
        return clothingItemRepository.findById(id).orElse(null);
    }

    // Method to save a new clothing item
    public ClothingItem save(ClothingItem clothingItem) {
        // Check if there is another clothing item with the same name
        if (clothingItemRepository.findByName(clothingItem.getName()) != null) {
            throw new ConflictException("Another record with the same name exists");
        }
        // Save the new clothing item
        return clothingItemRepository.save(clothingItem);
    }

    // Method to update an existing clothing item by its ID
    public ClothingItem update(Integer id, ClothingItem updatedClothingItem) {
        ClothingItem existingClothingItem = clothingItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Clothing item not found with id: " + id));

        // Update the existing clothing item
        existingClothingItem.setName(updatedClothingItem.getName());
        existingClothingItem.setCost(updatedClothingItem.getCost());
        existingClothingItem.setDescription(updatedClothingItem.getDescription());


        return clothingItemRepository.save(existingClothingItem);
    }

    // Method to delete a clothing item by its ID
    public void delete(Integer id) {
        clothingItemRepository.deleteById(id);
    }
}
