package com.ahmad.web3V.service;


import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ahmad.web3V.model.ApplicationUser;
import com.ahmad.web3V.model.LoginResponseDTO;
import com.ahmad.web3V.model.Role;
import com.ahmad.web3V.repository.RoleRepository;
import com.ahmad.web3V.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

    // Autowired instance of UserRepository for user-related operations
    @Autowired
    private UserRepository userRepository;

    // Autowired instance of RoleRepository for role-related operations
    @Autowired
    private RoleRepository roleRepository;

    // Autowired instance of PasswordEncoder for password encoding
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Autowired instance of AuthenticationManager for authentication
    @Autowired
    private AuthenticationManager authenticationManager;

    // Autowired instance of TokenService for JWT token generation
    @Autowired
    private TokenService tokenService;

    // Method to register a new user
    public ApplicationUser registerUser(String username, String password) {
        // Encode the password using the PasswordEncoder
        String encodedPassword = passwordEncoder.encode(password);

        // Retrieve the USER role from the RoleRepository
        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new RuntimeException("USER role not found"));

        // Create a Set of authorities with the USER role
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        // Save the new user with encoded password and authorities
        return userRepository.save(new ApplicationUser(0, username, encodedPassword, authorities));
    }

    // Method to handle user login
    public LoginResponseDTO loginUser(String username, String password) {
        try {
            // Attempt authentication using AuthenticationManager
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Generate JWT token using TokenService
            String token = tokenService.generateJwt(auth);

            // Return a LoginResponseDTO with the authenticated user and generated token
            return new LoginResponseDTO(userRepository.findByUsername(username).orElse(null), token);
        } catch (AuthenticationException e) {
            // Return a LoginResponseDTO with null user and an empty token in case of authentication failure
            return new LoginResponseDTO(null, "");
        }
    }
}

