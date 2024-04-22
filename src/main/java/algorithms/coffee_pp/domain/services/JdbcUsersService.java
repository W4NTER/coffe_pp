package algorithms.coffee_pp.domain.services;

import algorithms.coffee_pp.domain.respository.jdbc.JdbcAuthorityRepository;
import algorithms.coffee_pp.domain.respository.jdbc.JdbcUserRepository;
import algorithms.coffee_pp.dto.request.UserRequest;
import algorithms.coffee_pp.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcUsersService implements UsersService {

    private final JdbcAuthorityRepository jdbcAuthorityRepository;
    private final JdbcUserRepository jdbcUserRepository;

    public JdbcUsersService(JdbcAuthorityRepository jdbcAuthorityRepository, JdbcUserRepository jdbcUserRepository) {
        this.jdbcAuthorityRepository = jdbcAuthorityRepository;
        this.jdbcUserRepository = jdbcUserRepository;
    }


    @Override
    public void addUser(String username, String password) {
        jdbcUserRepository.addUser(username, password);
        jdbcAuthorityRepository.addAuthority(username, "USER");
    }

    @Override
    public List<UserResponse> findAll() {
        return jdbcUserRepository.findAll();
    }

    public UserResponse getUser(String username) {
        return jdbcUserRepository.getUser(username);
    }
}
