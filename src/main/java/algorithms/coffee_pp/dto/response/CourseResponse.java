package algorithms.coffee_pp.dto.response;

import java.time.OffsetDateTime;

public record CourseResponse (long courseId, String courseName, String description,
                              OffsetDateTime startDate, OffsetDateTime endDate) {
}
