package db;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    T get(int id);

    List<T> getAll();

    void add(T t) throws SQLException;

    void update(T t, String[] params);

    void delete(T t);
}