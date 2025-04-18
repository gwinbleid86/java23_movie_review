package kg.attractor.movie_review_java23.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    private String name;

    @Column(name = "release_year")
    private int releaseYear;

    private String description;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<MovieCastMember> movieCastMemberList;

    @Override
    public String toString() {
        return String.format("Фильм: <<%s>>,%nГод выпуска: %s,%nОписание: %s,%n%s", name, releaseYear, description, director.getId());
    }
}
