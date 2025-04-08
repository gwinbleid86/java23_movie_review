package kg.attractor.movie_review_java23.dao;

import kg.attractor.movie_review_java23.model.MovieImage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieImageDao {
    private final JdbcTemplate jdbcTemplate;

    public Optional<MovieImage> getImageById(long imageId) {
        String sql = "select * from movie_images where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new BeanPropertyRowMapper<>(MovieImage.class),
                                imageId
                        )
                )
        );
    }

    public void save(Long movieId, String filename) {
        String sql = "insert into movie_images (movie_id, file_name) values (?, ?)";
        jdbcTemplate.update(sql, movieId, filename);
    }

    public Optional<MovieImage> findByMovieId(long movieId) {
        String sql = "select * from movie_images where movie_id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MovieImage.class), movieId)
                )
        );
    }
}
