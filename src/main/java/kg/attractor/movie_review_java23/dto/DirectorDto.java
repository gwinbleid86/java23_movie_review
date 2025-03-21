package kg.attractor.movie_review_java23.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DirectorDto {
    private int id;
    private String fullName;
}
