package dev.java10x.movieflix.repository;

import dev.java10x.movieflix.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
