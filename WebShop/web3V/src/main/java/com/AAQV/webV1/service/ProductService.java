package com.AAQV.webV1.service;

import com.AAQV.webV1.error.ConflictException;
import com.AAQV.webV1.error.NotFoundException;
import com.AAQV.webV1.model.Product;
import com.AAQV.webV1.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {

    // Autowired instance of ClothingItemRepo for clothing item-related operations
    @Autowired
    private ProductRepo productRepository;

    // Method to retrieve all clothing items
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Method to retrieve a clothing item by its ID
    public Product getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    // Method to save a new clothing item
    public Product save(Product product) {
        // Check if there is another clothing item with the same name
        if (productRepository.findByName(product.getName()) != null) {
            throw new ConflictException("Another record with the same name exists");
        }
        // Save the new clothing item
        return productRepository.save(product);
    }

    // Method to update an existing clothing item by its ID
    public Product update(Integer id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Clothing item not found with id: " + id));

        // Update the existing clothing item
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setShortDescription(updatedProduct.getShortDescription());


        return productRepository.save(existingProduct);
    }

    // Method to delete a clothing item by its ID
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
