package com.ahmad.web3V.model;


public class LoginResponseDTO {
    // The user associated with the login response
    private ApplicationUser user;

    // JWT token for authentication
    private String jwt;

    // Default constructor
    public LoginResponseDTO() {
        super();
    }

    // Parameterized constructor to set user and JWT
    public LoginResponseDTO(ApplicationUser user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    // Getter and Setter methods

    // Getter for user
    public ApplicationUser getUser() {
        return this.user;
    }

    // Setter for user
    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    // Getter for JWT
    public String getJwt() {
        return this.jwt;
    }

    // Setter for JWT
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
