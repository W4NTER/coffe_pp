package algorithms.coffee_pp.domain.services.jdbc;

import algorithms.coffee_pp.domain.respository.CoursesToUsersRepository;
import algorithms.coffee_pp.domain.respository.jdbc.JdbcCoursesToUsersRepository;
import algorithms.coffee_pp.domain.services.CoursesToUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcCoursesToUsersService implements CoursesToUsersService {
    @Autowired
    private JdbcCoursesToUsersRepository jdbcCoursesToUsersRepository;

    @Override
    public void subscribe(long courseId, long userId) {
        jdbcCoursesToUsersRepository.add(courseId, userId);
    }

    @Override
    public List<Long> getAllByUser(long userId) {
        return jdbcCoursesToUsersRepository.getAllByUser(userId);
    }


}
