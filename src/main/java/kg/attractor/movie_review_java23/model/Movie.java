package kg.attractor.movie_review_java23.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private int id;
    private String name;
    private int year;
    private String description;
    private Director director;
    private List<Cast> cast;

    @Override
    public String toString() {
        return String.format("Фильм: <<%s>>,\nГод выпуска: %s,\nОписание: %s,\n%s,\nАктеры: %s", name, year, description, director, cast);
    }
}
