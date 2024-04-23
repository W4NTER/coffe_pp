package algorithms.coffee_pp.domain.services;

import java.util.List;

public interface DocumentsService {
    void addDoc(String filename, long userId);
    List<String> usersDocs(long userId);
}
