package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.ModuleRepository;
import algorithms.coffee_pp.dto.response.ModuleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class JdbcModuleRepository implements ModuleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addModule(long courseId, String title, OffsetDateTime startTime, OffsetDateTime endTime) {
        jdbcTemplate.update("insert into module " +
                "(module_title, course_id, start_date, end_date) values (?,?,?,?)",
                title, courseId, startTime, endTime);
    }

    @Override
    public List<ModuleResponse> findAll(long courseId) {
        return jdbcTemplate.query(
                "select module_title, module_id from module where course_id = ?",
                (rs, rowNum) ->
                new ModuleResponse(
                        rs.getString("module_title"),
                        rs.getLong("module_id")
                ), courseId);
    }
}
