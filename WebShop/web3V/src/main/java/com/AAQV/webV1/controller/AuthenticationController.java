package com.AAQV.webV1.controller;


import com.AAQV.webV1.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.AAQV.webV1.model.ApplicationUser;
import com.AAQV.webV1.model.LoginResponseDTO;
import com.AAQV.webV1.model.RegistrationDTO;

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
