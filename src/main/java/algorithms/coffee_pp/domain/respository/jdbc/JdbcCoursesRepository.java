package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.CoursesRepository;
import algorithms.coffee_pp.dto.response.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
@Repository
public class JdbcCoursesRepository implements CoursesRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long addCourse(String courseName, String description,
                          OffsetDateTime startDate, OffsetDateTime endDate) {
        jdbcTemplate.update("insert into courses " +
                "(course_title, description, start_date, end_date) values (?, ?, ?, ?)",
                courseName, description, startDate, endDate);
        return getCourse(courseName).courseId();
    }

    @Override
    public List<CourseResponse> findAll() {
        return jdbcTemplate.query("select" +
                        " course_id, course_title, description, start_date, end_date " +
                        "from courses",
                (rs, rowNum) -> getCourseResponse(rs));
    }

    @Override
    public CourseResponse getCourse(long courseId) {
        return jdbcTemplate.queryForObject(
                "select course_id, course_title, description, start_date, end_date " +
                        "from courses where course_id = ?",
                (rs, rowNum) -> getCourseResponse(rs), courseId);
    }

    public CourseResponse getCourse(String title) {
        return jdbcTemplate.queryForObject(
                "select course_id, course_title, description, start_date, end_date " +
                "from courses where course_title = ?", (rs, rowNum) -> getCourseResponse(rs), title);
    }

    public CourseResponse getCourseResponse(ResultSet resultSet) throws SQLException {
        return new CourseResponse(
                resultSet.getLong("course_id"),
                resultSet.getString("course_title"),
                resultSet.getString("description"),
                resultSet.getTimestamp("start_date")
                        .toInstant().atZone(ZoneOffset.systemDefault())
                        .toOffsetDateTime(),
                resultSet.getTimestamp("end_date")
                        .toInstant().atZone(ZoneOffset.systemDefault())
                        .toOffsetDateTime()
        );
    }
}
