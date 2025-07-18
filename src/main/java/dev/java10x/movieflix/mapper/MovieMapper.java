package dev.java10x.movieflix.mapper;

import dev.java10x.movieflix.controller.request.MovieRequest;
import dev.java10x.movieflix.controller.response.CategoryResponse;
import dev.java10x.movieflix.controller.response.MovieResponse;
import dev.java10x.movieflix.controller.response.StreamingResponse;
import dev.java10x.movieflix.entity.Category;
import dev.java10x.movieflix.entity.Movie;
import dev.java10x.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {



    public static Movie toMovie (MovieRequest movieRequest){

        List<Category> categories = movieRequest.categories().stream()
                .map(categoryID -> Category.builder().id(categoryID).build())
                .toList();

        List<Streaming> streamings = movieRequest.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(movieRequest.title())
                .rating(movieRequest.rating())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .streamings(streamings)
                .categories(categories)
                .build();
    }

    public static MovieResponse toMovieResponse (Movie movie) {

        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings().stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .description(movie.getDescription())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

}
