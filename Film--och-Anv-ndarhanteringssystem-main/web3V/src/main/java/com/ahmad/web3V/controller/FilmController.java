package com.ahmad.web3V.controller;

import com.ahmad.web3V.model.Film;
import com.ahmad.web3V.service.AuthenticationService;
import com.ahmad.web3V.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/film")
public class FilmController {

    // Autowired instance of FilmService for handling film-related operations
    @Autowired
    private FilmService filmService;

    // Autowired instance of AuthenticationService (consider renaming userService to avoid confusion)
    @Autowired
    private AuthenticationService userService;

    // Endpoint to get all films
    @GetMapping(value = {"/hämta-films"})
    public ResponseEntity<List<Film>> getAllFilms() {
        // Retrieve all films from the FilmService
        List<Film> result = filmService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint to get a film by its ID
    @GetMapping("/hämta{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable  Integer id) {
        // Retrieve a film by its ID from the FilmService
        Film result = filmService.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Endpoint to create a new film
    @PostMapping(value = {"/post-film"})
    public ResponseEntity<Film> createNewFilm(@RequestBody Film film) {
        // Save the new film using the FilmService
        Film result = filmService.save(film);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Endpoint to delete a film by its ID
    @DeleteMapping("/delete{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Integer id) {
        // Delete the film by its ID using the FilmService
        filmService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
