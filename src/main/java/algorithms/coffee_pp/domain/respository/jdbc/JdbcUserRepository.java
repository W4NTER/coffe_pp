package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.UsersRepository;
import algorithms.coffee_pp.dto.request.UserRequest;
import algorithms.coffee_pp.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JdbcUserRepository implements UsersRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder encoder;

    public JdbcUserRepository(
            JdbcTemplate jdbcTemplate,
            PasswordEncoder encoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void addUser(String username, String password) {
        jdbcTemplate.update("insert into users (username, password, enabled) values (?, ?, ?)",
                username, encoder.encode(password), true);
    }

    public UserResponse getUser(String username) {
        return jdbcTemplate.queryForObject(
                "select username, password, enabled, user_id from users where username = ?",
                ((rs, rowNum) ->
                        new UserResponse(
                                rs.getLong("user_id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("enabled")
                        )), username);
    }

    @Override
    public void remove() {

    }

    @Override
    public List<UserResponse> findAll() {
        return jdbcTemplate.query(
                "select user_id, username, password, enabled, priority from users",
        (rs, rowNum) -> {
                    return new UserResponse(
                            rs.getLong("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("enabled")
                    );
        });
    }
}
