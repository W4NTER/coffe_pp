package algorithms.coffee_pp.domain.services.jdbc;

import algorithms.coffee_pp.domain.respository.jdbc.JdbcCoursesRepository;
import algorithms.coffee_pp.domain.respository.jdbc.JdbcCoursesToUsersRepository;
import algorithms.coffee_pp.domain.services.CoursesService;
import algorithms.coffee_pp.dto.response.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class JdbcCourseService implements CoursesService {
    private final JdbcCoursesRepository jdbcCoursesRepository;
    private final JdbcCoursesToUsersRepository jdbcCoursesToUsersRepository;

    public JdbcCourseService(JdbcCoursesRepository jdbcCoursesRepository, JdbcCoursesToUsersRepository jdbcCoursesToUsersRepository) {
        this.jdbcCoursesRepository = jdbcCoursesRepository;
        this.jdbcCoursesToUsersRepository = jdbcCoursesToUsersRepository;
    }

    @Override
    public void addCourse(long userId, String courseTitle, String description) {
        long courseId = jdbcCoursesRepository.addCourse(courseTitle, description,
                OffsetDateTime.now(), OffsetDateTime.now());
        jdbcCoursesToUsersRepository.add(courseId, userId);
    }

    @Override
    public List<CourseResponse> findAll() {
        return jdbcCoursesRepository.findAll();
    }
}
