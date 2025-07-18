package dev.java10x.movieflix.service;

import dev.java10x.movieflix.entity.Category;
import dev.java10x.movieflix.entity.Movie;
import dev.java10x.movieflix.entity.Streaming;
import dev.java10x.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {


    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieService(MovieRepository movieRepository, CategoryService categoryService, StreamingService streamingService) {
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
    }

    public Movie save(Movie movie) {
        movie.setCategories(this.findCategory(movie.getCategories()));
        movie.setStreamings(this.findStreaming(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovie(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> findByCategories(Long categoryId) {
        return movieRepository.findByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public Optional<Movie> update(Long movieId, Movie movie) {
        Optional<Movie> optMovie = movieRepository.findById(movieId);
        if (optMovie.isPresent()) {

            List<Category> categories = this.findCategory(movie.getCategories());
            List<Streaming> streamings = this.findStreaming(movie.getStreamings());

            Movie updateMovie = optMovie.get();

            updateMovie.setTitle(movie.getTitle());
            updateMovie.setDescription(movie.getDescription());
            updateMovie.setRating(movie.getRating());
            updateMovie.setReleaseDate(movie.getReleaseDate());

            updateMovie.getCategories().clear();
            updateMovie.getCategories().addAll(categories);

            updateMovie.getStreamings().clear();
            updateMovie.getStreamings().addAll(streamings);

            movieRepository.save(updateMovie);
            return Optional.of(updateMovie);
        }

        return Optional.empty();
    }

    private List<Category> findCategory(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach
                (category -> categoryService.listById(category.getId()).ifPresent(categoriesFound::add));
        return categoriesFound;
    }

    private List<Streaming> findStreaming(List<Streaming> streamings) {
        List<Streaming> streamingFound = new ArrayList<>();
        streamings.forEach
                (streaming -> streamingService.findById(streaming.getId()).ifPresent(streamingFound::add));
        return streamingFound;
    }



}
