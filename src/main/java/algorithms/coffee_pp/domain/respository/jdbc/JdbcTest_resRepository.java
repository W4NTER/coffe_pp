package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.Test_resRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTest_resRepository implements Test_resRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addRes(long userId, long courseId, long result) {
        jdbcTemplate.update("insert into test_res (user_id, course_id, result) " +
                "values (?, ?, ?)", userId, courseId, result);
    }

    @Override
    public long getResByUserAndCourse(long userId, long courseId) {
        return jdbcTemplate.queryForObject("select result from test_res where user_id = ? and course_id = ?",
                Long.class, userId, courseId);
    }
}
