package kg.attractor.movie_review_java23.dao.mapper;

import kg.attractor.movie_review_java23.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
