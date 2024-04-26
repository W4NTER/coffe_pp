package algorithms.coffee_pp.domain.services.jdbc;

import algorithms.coffee_pp.domain.respository.jdbc.JdbcQuestionsRepository;
import algorithms.coffee_pp.domain.services.QuestionsService;
import algorithms.coffee_pp.dto.response.QuestionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JdbcQuestionsService implements QuestionsService {
    @Autowired
    private JdbcQuestionsRepository jdbcQuestionsRepository;

    @Override
    public void addQuestion(long courseId, String question, String correctAnswer, String options) {
        jdbcQuestionsRepository.addQuestion(courseId, question, correctAnswer, options);
    }

    @Override
    public List<QuestionsResponse> findAll(long testId) {
        var a = jdbcQuestionsRepository.findAll(testId);
        return a.stream().map(b ->
                new QuestionsResponse(
                        b.courseId(),
                        b.question(),
                        b.correctAnswer(),
                        b.options().split(","))).toList();
    }
}
