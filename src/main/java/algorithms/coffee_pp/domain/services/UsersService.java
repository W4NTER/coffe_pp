package algorithms.coffee_pp.domain.services;

import algorithms.coffee_pp.dto.request.UserRequest;
import algorithms.coffee_pp.dto.response.UserResponse;

import java.util.List;

public interface UsersService {
    void addUser(String username, String password);
    List<UserResponse> findAll();
}
