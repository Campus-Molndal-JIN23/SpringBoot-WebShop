package com.ahmad.web3V.controller;

import com.ahmad.web3V.error.NotFoundException;
import com.ahmad.web3V.model.ApplicationUser;
import com.ahmad.web3V.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    // Autowired instance of UserService for handling user-related operations
    @Autowired
    private UserService userService;

    // Endpoint to delete a user by their ID
    @DeleteMapping("/delete{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        try {
            // Delete the user by their ID using the UserService
            userService.deleteByUserId(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (NotFoundException e) {
            // Return a 404 Not Found response if the user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Endpoint to get all users
    @GetMapping(value = {"/hämta-users"})
    public ResponseEntity<List<ApplicationUser>> getAllUsers() {
        // Retrieve all users from the UserService
        List<ApplicationUser> result = userService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

