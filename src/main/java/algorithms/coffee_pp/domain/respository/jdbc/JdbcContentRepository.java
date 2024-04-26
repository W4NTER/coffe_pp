package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.ContentRepository;
import algorithms.coffee_pp.dto.response.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcContentRepository implements ContentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addContent(String title, String description, String imagePath, String body, long moduleId) {
        jdbcTemplate.update(
                "insert into content " +
                        "(content_title, description, image_path, body, module_id)" +
                        " VALUES (?,?,?,?,?)",
                title, description, imagePath, body, moduleId);
    }

    @Override
    public ContentResponse getContent(long moduleID) {
        return jdbcTemplate.queryForObject(
                "select content_title, description, image_path, body, module_id " +
                        "from content where module_id = ?",
                (rs, rowNum) -> contentResponse(rs), moduleID);
    }

    private ContentResponse contentResponse(ResultSet resultSet) throws SQLException {
        return new ContentResponse(
                resultSet.getString("content_title"),
                resultSet.getString("description"),
                resultSet.getString("image_path"),
                resultSet.getString("body"),
                resultSet.getLong("module_id")
        );
    }
}
