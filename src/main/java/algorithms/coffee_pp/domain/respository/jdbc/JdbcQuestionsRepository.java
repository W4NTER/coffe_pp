package algorithms.coffee_pp.domain.respository.jdbc;

import algorithms.coffee_pp.domain.respository.QuestionsRepository;
import algorithms.coffee_pp.dto.request.QuestionsRequest;
import algorithms.coffee_pp.dto.response.QuestionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcQuestionsRepository implements QuestionsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addQuestion(long testId, String question, String correctAnswer, String options) {
        jdbcTemplate.update("insert into questions (course_id, question, correct_answer, options) VALUES (?, ?, ?, ?)",
                testId, question, correctAnswer,options);
    }

    @Override
    public List<QuestionsRequest> findAll(long testId) {
        return jdbcTemplate.query(
                "select course_id, question, correct_answer, options" +
                        " from questions where course_id = ?",
                (rs, rowNum) -> new QuestionsRequest(
                        rs.getLong("course_id"),
                        rs.getString("question"),
                        rs.getString("correct_answer"),
                        rs.getString("options")
                ), testId);
    }
}
