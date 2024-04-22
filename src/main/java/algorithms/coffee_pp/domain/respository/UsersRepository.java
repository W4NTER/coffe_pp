package algorithms.coffee_pp.domain.respository;

import algorithms.coffee_pp.dto.request.UserRequest;
import algorithms.coffee_pp.dto.response.UserResponse;

import java.util.List;

public interface UsersRepository {
    void addUser(String username, String password);
    void remove();
    List<UserResponse> findAll();
}
