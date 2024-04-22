package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcAuthorityRepository implements AuthoritiesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addAuthority(String username, String authority) {
        jdbcTemplate.update("insert into authorities(username, authority) values (?, ?)",
                username, authority);
    }
}
