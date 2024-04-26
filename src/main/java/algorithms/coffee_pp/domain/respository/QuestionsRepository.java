package algorithms.coffee_pp.domain.respository;

import algorithms.coffee_pp.dto.request.QuestionsRequest;
import algorithms.coffee_pp.dto.response.QuestionsResponse;

import java.util.List;

public interface QuestionsRepository {
    void addQuestion(long testId, String question, String correctAnswer, String options);
    List<QuestionsRequest> findAll(long testId);
}
