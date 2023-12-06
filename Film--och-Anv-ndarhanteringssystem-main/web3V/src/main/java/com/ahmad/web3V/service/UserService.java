package com.ahmad.web3V.service;


import java.util.List;
import java.util.Optional;
import com.ahmad.web3V.error.NotFoundException;
import com.ahmad.web3V.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ahmad.web3V.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    // Autowired instance of PasswordEncoder for password encoding
    @Autowired
    private PasswordEncoder encoder;

    // Autowired instance of UserRepository for user-related operations
    @Autowired
    private UserRepository userRepository;

    // Implementation of the loadUserByUsername method from UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user details by username from UserRepository
        System.out.println("In the user details service");
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not valid"));
    }

    // Method to delete a user by their ID, accessible only to users with ADMIN role
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteByUserId(Integer userId) {
        // Attempt to retrieve the user by ID from UserRepository
        Optional<ApplicationUser> userOptional = userRepository.findById(userId);

        // Delete the user if present, otherwise, throw NotFoundException
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new NotFoundException("User not found");
        }
    }

    // Method to retrieve all users, accessible only to users with ADMIN role
    @PreAuthorize("hasRole('ADMIN')")
    public List<ApplicationUser> findAll() {
        return userRepository.findAll();
    }
}
