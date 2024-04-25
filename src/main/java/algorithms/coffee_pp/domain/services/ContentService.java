package algorithms.coffee_pp.domain.services;

import algorithms.coffee_pp.dto.response.ContentResponse;

import java.util.List;

public interface ContentService {
    ContentResponse getContent(long moduleId);
}
