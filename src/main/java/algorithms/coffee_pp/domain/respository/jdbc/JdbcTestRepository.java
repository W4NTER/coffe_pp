package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTestRepository implements TestRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addTest(String answer) {
        jdbcTemplate.update("insert into tests (answer)" +
                " values (?)", answer);
    }

}
