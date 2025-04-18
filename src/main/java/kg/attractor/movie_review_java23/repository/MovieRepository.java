package kg.attractor.movie_review_java23.repository;

import kg.attractor.movie_review_java23.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true,
            value = "select * from MOVIE where RELEASE_YEAR = :year"
    )
//    @Query("select m from Movie m where m.releaseYear = :year")
    List<Movie> findAllByReleaseYear(int year);

    @Query("select m.id," +
            "m.name," +
            "m.releaseYear," +
            "m.description," +
            "m.director " +
            "from Movie m " +
            "inner join MovieCastMember mcm on m.id = mcm.movie.id " +
            "inner join Cast c on mcm.cast.id = c.id " +
            "where m.releaseYear > :releaseYear " +
            "and c.fullname like '%:castName%'")
    List<Movie> selectMoviesByCastNameAndReleaseYear(String castName, int releaseYear);
}
