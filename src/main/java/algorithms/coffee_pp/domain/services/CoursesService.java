package algorithms.coffee_pp.domain.services;

import algorithms.coffee_pp.dto.response.CourseResponse;

import java.time.OffsetDateTime;
import java.util.List;

public interface CoursesService {
    void addCourse(long userId, String courseTitle, String description);
    List<CourseResponse> findAll();
    CourseResponse getCourse(long courseId);
}
