package algorithms.coffee_pp.domain.respository;

import java.util.List;

public interface CoursesToUsersRepository {
    void add(long coursesId, long userId);
    List<Long> getAllByUser(long userId);
}
