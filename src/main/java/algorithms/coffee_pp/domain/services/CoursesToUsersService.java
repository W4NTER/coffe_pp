package algorithms.coffee_pp.domain.services;

import java.util.List;

public interface CoursesToUsersService {
    void subscribe(long courseId, long userId);
    List<Long> getAllByUser(long userId);
}
