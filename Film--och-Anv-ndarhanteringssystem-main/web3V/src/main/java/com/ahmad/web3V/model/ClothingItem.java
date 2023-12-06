package com.ahmad.web3V.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clothes")
public class ClothingItem {
    // Primary key for the clothing item entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothing_item_id")
    private Integer id;

    // Name of the clothing item
    private String name;

    // Cost of the clothing item
    private double cost;

    // Description of the clothing item
    private String description;

    // User ID associated with the clothing item
}
