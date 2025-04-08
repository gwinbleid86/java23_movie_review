package kg.attractor.movie_review_java23.service;

import kg.attractor.movie_review_java23.dto.MovieImageDto;
import org.springframework.http.ResponseEntity;

public interface ImageService {
    String saveImage(MovieImageDto movieImageDto);

    ResponseEntity<?> findByName(String imageName);

    ResponseEntity<?> findById(long imageId);

    ResponseEntity<?> findByMovieId(long movieId);
}
