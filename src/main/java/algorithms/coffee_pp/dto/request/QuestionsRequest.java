package algorithms.coffee_pp.dto.request;

public record QuestionsRequest(
        long courseId,
        String question,
        String correctAnswer,
        String options) {
}
