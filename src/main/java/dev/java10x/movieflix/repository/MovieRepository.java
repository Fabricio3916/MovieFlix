package dev.java10x.movieflix.repository;

import dev.java10x.movieflix.entity.Category;
import dev.java10x.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByCategories(List<Category> categories);



}
