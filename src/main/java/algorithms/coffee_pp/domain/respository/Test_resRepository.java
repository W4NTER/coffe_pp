package algorithms.coffee_pp.domain.respository;

public interface Test_resRepository {
    void addRes(long userId, long courseId, long result);
    long getResByUserAndCourse(long userId, long courseId);
}
