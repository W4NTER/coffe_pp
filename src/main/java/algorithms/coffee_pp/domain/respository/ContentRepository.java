package algorithms.coffee_pp.domain.respository;

import algorithms.coffee_pp.dto.response.ContentResponse;

import java.util.List;

public interface ContentRepository {
    void addContent(String title, String description, String imagePath, String body, long moduleId);
    ContentResponse getContent(long moduleId);
}
