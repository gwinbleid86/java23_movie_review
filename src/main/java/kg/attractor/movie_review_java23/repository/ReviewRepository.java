package kg.attractor.movie_review_java23.repository;

import kg.attractor.movie_review_java23.model.Review;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r " +
            "where r.movie.id = :movieId")
    List<Review> findByMovie_Id(long movieId, Sort sort);
}