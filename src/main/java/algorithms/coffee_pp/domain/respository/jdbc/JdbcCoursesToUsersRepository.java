package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.CoursesToUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCoursesToUsersRepository implements CoursesToUsersRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(long coursesId, long userId) {
        jdbcTemplate.update("insert into courses_to_users (course_id, user_id) values (?, ?)",
                coursesId, userId);
    }
}
