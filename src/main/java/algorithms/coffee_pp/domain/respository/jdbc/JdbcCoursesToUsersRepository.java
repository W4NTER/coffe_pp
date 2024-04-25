package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.CoursesToUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCoursesToUsersRepository implements CoursesToUsersRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(long coursesId, long userId) {
        jdbcTemplate.update("insert into courses_to_users (course_id, user_id) values (?, ?)",
                coursesId, userId);
    }

    @Override
    public List<Long> getAllByUser(long userId) {
        return jdbcTemplate.queryForList(
                "SELECT course_id FROM courses_to_users WHERE user_id = ?",
                Long.class, userId);
    }
}
