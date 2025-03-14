package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.dto.MovieDto;
import kg.attractor.movie_review_java23.model.Director;
import kg.attractor.movie_review_java23.model.Movie;
import kg.attractor.movie_review_java23.service.MovieService;
import kg.attractor.movie_review_java23.util.FileUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final List<Movie> movies;

    public MovieServiceImpl() {
        this.movies = new FileUtil().getMovies("movies.json");
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movies.stream()
                .map(e -> MovieDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .year(e.getYear())
                        .description(e.getDescription())
                        .build())
                .toList();
    }

    @Override
    public MovieDto getMovieById(String movieId) {
        int id = Integer.parseInt(movieId);
        return movies.stream()
                .filter(m -> m.getId() == id)
                .map(e -> MovieDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .year(e.getYear())
                        .description(e.getDescription())
                        .build())
                .findAny()
                .orElseThrow();
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        movies.add(Movie.builder()
                .id(movies.size() + 1)
                .name(movieDto.getName())
                .year(LocalDate.now().getYear())
                .description(movieDto.getDescription())
                .cast(new ArrayList<>())
                .director(new Director())
                .build());
    }
}
