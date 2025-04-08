package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.dao.MovieDao;
import kg.attractor.movie_review_java23.dto.MovieDto;
import kg.attractor.movie_review_java23.exceptions.MovieNotFoundException;
import kg.attractor.movie_review_java23.model.Movie;
import kg.attractor.movie_review_java23.service.DirectorService;
import kg.attractor.movie_review_java23.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;
    private final DirectorService directorService;

    @Override
    public List<MovieDto> getAllMovies() {
        SecurityContext sc = SecurityContextHolder.getContext();
        var principal = sc.getAuthentication().getName();
        log.info("principal: {}", principal);

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

    @Override
    public List<MovieDto> getMovieListPaging(int page, int size) {
        int offset = page * size;
        int total = movieDao.countMovies();

        int lastPage = total % size;

        if (page <= 0) {
            offset = 0;
        }
        if (page > lastPage) {
            offset = total - lastPage;
        }
        if (lastPage == 0) {
            offset = total - size;
        }

        List<Movie> movies = movieDao.getMoviesWithPagination(size, offset);
        return movies.stream()
                .map(e -> MovieDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .year(e.getReleaseYear())
                        .description(e.getDescription())
                        .build())
                .toList();
    }
}
