package algorithms.coffee_pp.dto.response;

public record QuestionsResponse(
        long courseId,
        String question,
        String correctAnswer,
        String[] options) {
}
