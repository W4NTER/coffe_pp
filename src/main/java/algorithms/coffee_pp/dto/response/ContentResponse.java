package algorithms.coffee_pp.dto.response;

public record ContentResponse (
        String title,
        String description,
        String imagePath,
        String body,
        long moduleId
        ) {
}
