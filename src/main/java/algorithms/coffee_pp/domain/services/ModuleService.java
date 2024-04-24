package algorithms.coffee_pp.domain.services;

import algorithms.coffee_pp.dto.response.ModuleResponse;

import java.util.List;

public interface ModuleService {
    void addModule(long courseId, String title);
    List<ModuleResponse> findAll(long courseId);
}
