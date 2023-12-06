package com.ahmad.web3V.repository;

import com.ahmad.web3V.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmRepo extends JpaRepository<Film, Integer>{

    Film findByTitle(String title);

    List<Film> findByUserId(Integer userId);



}