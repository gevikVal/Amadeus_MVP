package fair.com.example.gevik.amadeus.db;

import java.util.List;

/**
 * Created by gvalijani on 10/20/17.
 */

public interface DbRepository<T> {
    void addOrUpdate(T item);

    void addOrUpdate(Iterable<T> items);

    void remove(String itemId);

    void removeAll();

    List<T> queryAll();

    List<T> queryAll(String sort);

    T query(String itemId);
}
