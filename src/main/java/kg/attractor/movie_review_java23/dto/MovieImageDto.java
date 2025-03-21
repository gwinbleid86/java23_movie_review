package kg.attractor.movie_review_java23.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MovieImageDto {
    private MultipartFile file;
    private Long movieId;
}
