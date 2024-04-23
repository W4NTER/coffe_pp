package algorithms.coffee_pp.domain.respository;

import java.util.List;

public interface DocumentsRepository {
    void addFile(String fileName, long userId);
    List<String> findAll(long userId);
}
