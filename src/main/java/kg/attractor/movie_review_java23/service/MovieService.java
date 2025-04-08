package kg.attractor.movie_review_java23.service;

import kg.attractor.movie_review_java23.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();

    MovieDto getMovieById(String movieId);

    void createMovie(MovieDto movieDto);

    List<MovieDto> getMovieListPaging(int size, int page);
}
