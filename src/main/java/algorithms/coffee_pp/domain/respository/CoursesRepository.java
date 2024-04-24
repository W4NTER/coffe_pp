package algorithms.coffee_pp.domain.respository;

import algorithms.coffee_pp.dto.response.CourseResponse;

import java.time.OffsetDateTime;
import java.util.List;

public interface CoursesRepository {
    long addCourse(String courseName, String description,
                   OffsetDateTime startDate, OffsetDateTime endDate);
    List<CourseResponse> findAll();
    CourseResponse getCourse(long courseId);
}
