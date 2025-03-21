package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.dao.MovieDao;
import kg.attractor.movie_review_java23.dto.MovieDto;
import kg.attractor.movie_review_java23.exceptions.MovieNotFoundException;
import kg.attractor.movie_review_java23.model.Movie;
import kg.attractor.movie_review_java23.service.DirectorService;
import kg.attractor.movie_review_java23.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;
    private final DirectorService directorService;

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieDao.getMovies();
        return movies.stream()
                .map(e -> MovieDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .year(e.getReleaseYear())
                        .description(e.getDescription())
                        .build())
                .toList();
    }

    @Override
    public MovieDto getMovieById(String movieId) {
        int id = Integer.parseInt(movieId);
        Movie movie = movieDao.getMovieById(id)
                .orElseThrow(MovieNotFoundException::new);
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .year(movie.getReleaseYear())
                .description(movie.getDescription())
                .director(directorService.getDirectorById(movie.getDirectorId()))
                .build();
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        List<Movie> movies = movieDao.getMovies();
        movies.add(Movie.builder()
                .id(movies.size() + 1)
                .name(movieDto.getName())
                .releaseYear(LocalDate.now().getYear())
                .description(movieDto.getDescription())
//                .cast(new ArrayList<>())
                .directorId(1)
                .build());
    }
}
