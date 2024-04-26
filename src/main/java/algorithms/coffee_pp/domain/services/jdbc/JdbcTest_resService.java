package algorithms.coffee_pp.domain.services.jdbc;

import algorithms.coffee_pp.domain.respository.jdbc.JdbcTest_resRepository;
import algorithms.coffee_pp.domain.services.Test_resService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcTest_resService implements Test_resService {
    @Autowired
    private JdbcTest_resRepository jdbcTestResRepository;

    @Override
    public void addRes(long userId, long courseId, long result) {
        jdbcTestResRepository.addRes(userId, courseId, result);
    }

    @Override
    public long getResByUserAndCourse(long userId, long courseId) {
        return jdbcTestResRepository.getResByUserAndCourse(userId, courseId);
    }
}
