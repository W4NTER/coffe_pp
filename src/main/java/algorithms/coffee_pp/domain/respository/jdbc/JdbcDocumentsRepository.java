package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcDocumentsRepository implements DocumentsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addFile(String fileName, long userId) {
        jdbcTemplate.update("insert into documents (user_id, filename) VALUES (?, ?)",
                userId, fileName);
    }

    @Override
    public List<String> findAll(long userId) {
        return jdbcTemplate.queryForList(
                "SELECT fileName FROM documents WHERE user_id = ?",
                String.class,
                userId);
    }

}
