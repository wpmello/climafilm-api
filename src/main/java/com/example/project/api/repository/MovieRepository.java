package com.example.project.api.repository;

import com.example.project.api.model.themovie.BodyMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<BodyMovie, Integer> {
}
