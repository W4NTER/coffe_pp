package algorithms.coffee_pp.domain.respository;

import algorithms.coffee_pp.dto.response.ModuleResponse;

import java.time.OffsetDateTime;
import java.util.List;

public interface ModuleRepository {
    Long addModule(
            long courseId, String title,
            OffsetDateTime startTime, OffsetDateTime endTime);
    List<ModuleResponse> findAll(long courseId);
}
