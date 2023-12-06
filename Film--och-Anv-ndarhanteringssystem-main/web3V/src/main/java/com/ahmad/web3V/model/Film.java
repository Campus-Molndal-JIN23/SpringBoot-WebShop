package com.ahmad.web3V.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "films")
public class Film {
    // Primary key for the film entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer id;

    // Title of the film
    private String title;

    // Description of the film
    private String description;

    // Timestamp representing when the film was created or modified
    private long timestamp;

    // User ID associated with the film
    @Column(name = "user_id")
    private Integer userId;
}



