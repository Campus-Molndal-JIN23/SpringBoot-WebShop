package com.ahmad.web3V.model;


import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    // Primary key for the role entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    // Authority associated with the role
    private String authority;

    // Default constructor
    public Role() {
        super();
    }

    // Parameterized constructor to set authority
    public Role(String authority) {
        this.authority = authority;
    }

    // Parameterized constructor to set roleId and authority
    public Role(Integer roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    // Implementation of the GrantedAuthority interface method
    @Override
    public String getAuthority() {
        return this.authority;
    }

    // Setter for authority
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    // Getter for roleId
    public Integer getRoleId() {
        return this.roleId;
    }

    // Setter for roleId
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
