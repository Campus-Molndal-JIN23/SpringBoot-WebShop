package com.ahmad.web3V.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ahmad.web3V.model.ApplicationUser;
import com.ahmad.web3V.model.LoginResponseDTO;
import com.ahmad.web3V.model.RegistrationDTO;
import com.ahmad.web3V.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    // Autowired instance of AuthenticationService for handling user authentication operations
    @Autowired
    private AuthenticationService authenticationService;

    // Endpoint for user registration
    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        // Delegate registration logic to the AuthenticationService
        // and return the registered ApplicationUser
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    // Endpoint for user login
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        // Delegate login logic to the AuthenticationService
        // and return the generated LoginResponseDTO
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
