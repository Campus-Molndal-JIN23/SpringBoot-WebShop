package com.ahmad.web3V.model;


public class RegistrationDTO {
    // Username for user registration
    private String username;

    // Password for user registration
    private String password;

    // Default constructor
    public RegistrationDTO() {
        super();
    }

    // Parameterized constructor to set username and password
    public RegistrationDTO(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    // Getter and Setter methods

    // Getter for username
    public String getUsername() {
        return this.username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return this.password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // toString method for displaying registration information
    @Override
    public String toString() {
        return "Registration info: username: " + this.username + " password: " + this.password;
    }
}
