package algorithms.coffee_pp.domain.services.jdbc;

import algorithms.coffee_pp.domain.respository.jdbc.JdbcDocumentsRepository;
import algorithms.coffee_pp.domain.services.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcDocumentsService implements DocumentsService {
    @Autowired
    private JdbcDocumentsRepository jdbcDocumentsRepository;

    @Override
    public void addDoc(String filename, long userId) {
        jdbcDocumentsRepository.addFile(filename, userId);
    }

    @Override
    public List<String> usersDocs(long userId) {
        return jdbcDocumentsRepository.findAll(userId);
    }
}
