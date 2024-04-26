package algorithms.coffee_pp.domain.services;

public interface Test_resService {
    void addRes(long userId, long courseId, long result);
    long getResByUserAndCourse(long userId, long courseId);
}
