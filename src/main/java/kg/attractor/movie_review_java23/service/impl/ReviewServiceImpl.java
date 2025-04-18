package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.dto.ReviewDto;
import kg.attractor.movie_review_java23.repository.ReviewRepository;
import kg.attractor.movie_review_java23.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> findByMovieId(Long movieId) {
//        Sort sort = Sort.by(Sort.Order.asc("reviewer"));
        var reviewers = reviewRepository.findByMovie_Id(movieId, Sort.by(Sort.Order.asc("reviewer")));
        return reviewers.stream()
                .map(e -> ReviewDto
                        .builder()
                        .id(e.getId())
                        .comment(e.getComment())
                        .rating(e.getRating())
                        .username(e.getUser().getUsername())
                        .build())
                .toList();
    }
}
