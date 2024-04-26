package algorithms.coffee_pp.domain.services;

import algorithms.coffee_pp.dto.response.QuestionsResponse;

import java.util.List;

public interface QuestionsService {
    void addQuestion(long courseId, String question, String correctAnswer, String options);
    List<QuestionsResponse> findAll(long testId);
}
