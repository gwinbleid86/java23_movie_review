package kg.attractor.movie_review_java23.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private int id;
    private String name;
    private String password;
}
