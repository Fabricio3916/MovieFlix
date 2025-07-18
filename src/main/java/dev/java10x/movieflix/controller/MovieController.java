package dev.java10x.movieflix.controller;

import dev.java10x.movieflix.controller.request.MovieRequest;
import dev.java10x.movieflix.controller.response.MovieResponse;
import dev.java10x.movieflix.entity.Movie;
import dev.java10x.movieflix.mapper.MovieMapper;
import dev.java10x.movieflix.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save (@RequestBody MovieRequest request) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(request));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies().stream()
                .map(MovieMapper::toMovieResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable Long movieId) {
        return movieService.getMovie(movieId)
                .map(movie ->  ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long movieId, @RequestBody MovieRequest request) {
        return movieService.update(movieId, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Optional<Movie> optionalMovie = movieService.getMovie(id);
        if (optionalMovie.isPresent()) {
            movieService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> searchMovie(@RequestParam Long categoryId) {
        return ResponseEntity.ok(movieService.findByCategories(categoryId).stream()
                .map(MovieMapper::toMovieResponse).toList());
    }


}
