package kg.attractor.movie_review_java23.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieImage {
    private Long id;
    private Long movieId;
    private String fileName;
}
