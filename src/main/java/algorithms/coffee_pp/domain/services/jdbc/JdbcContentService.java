package algorithms.coffee_pp.domain.services.jdbc;

import algorithms.coffee_pp.domain.respository.jdbc.JdbcContentRepository;
import algorithms.coffee_pp.domain.services.ContentService;
import algorithms.coffee_pp.dto.response.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcContentService implements ContentService {
    @Autowired
    private JdbcContentRepository jdbcContentRepository;

    @Override
    public ContentResponse getContent(long moduleId) {
        return jdbcContentRepository.getContent(moduleId);
    }
}
