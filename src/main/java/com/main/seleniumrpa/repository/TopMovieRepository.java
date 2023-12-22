package com.main.seleniumrpa.repository;

import com.main.seleniumrpa.model.TopMovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopMovieRepository extends JpaRepository<TopMovieModel, Integer> {
}
