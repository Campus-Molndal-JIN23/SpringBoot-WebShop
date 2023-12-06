package com.ahmad.web3V.service;

import com.ahmad.web3V.error.ConflictException;
import com.ahmad.web3V.model.Film;
import com.ahmad.web3V.repository.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FilmService {

    // Autowired instance of FilmRepo for film-related operations
    @Autowired
    private FilmRepo filmRepository;

    // Method to retrieve all films
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    // Method to retrieve films by user ID
    public List<Film> findByUser(Integer id) {
        return filmRepository.findByUserId(id);
    }

    // Method to retrieve a film by its ID
    public Film getById(Integer id){
        return filmRepository.findById(id).orElse(null);
    }

    // Method to save a new film
    public Film save(Film film) {
        // Check if there is another film with the same title
        if (filmRepository.findByTitle(film.getTitle()) != null) {
            throw new ConflictException("Another record with the same title exists");
        }
        // Save the new film
        return filmRepository.save(film);
    }

    // Method to delete a film by its ID
    public void delete(Integer id) {
        filmRepository.deleteById(id);
    }
}






