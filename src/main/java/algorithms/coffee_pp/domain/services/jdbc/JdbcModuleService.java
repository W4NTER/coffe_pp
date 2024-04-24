package algorithms.coffee_pp.domain.services.jdbc;

import algorithms.coffee_pp.domain.respository.jdbc.JdbcModuleRepository;
import algorithms.coffee_pp.domain.services.ModuleService;
import algorithms.coffee_pp.dto.response.ModuleResponse;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class JdbcModuleService implements ModuleService {
    private final JdbcModuleRepository jdbcModuleRepository;

    public JdbcModuleService(JdbcModuleRepository jdbcModuleRepository) {
        this.jdbcModuleRepository = jdbcModuleRepository;
    }

    @Override
    public void addModule(long courseId, String title) {
        jdbcModuleRepository.addModule(courseId, title, OffsetDateTime.now(), OffsetDateTime.now());
    }

    @Override
    public List<ModuleResponse> findAll(long courseId) {
        return jdbcModuleRepository.findAll(courseId);
    }
}
