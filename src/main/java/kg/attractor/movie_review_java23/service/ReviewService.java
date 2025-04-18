package kg.attractor.movie_review_java23.service;

import kg.attractor.movie_review_java23.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> findByMovieId(Long movieId);
}
