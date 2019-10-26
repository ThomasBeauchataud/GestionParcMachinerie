package models.common;

import java.util.List;

public interface CommonDaoInterface<T> {

    void insert(T object);

    void update(T object);

    T getById(int id);

    List<T> getAll();

    void deleteById(int id);

}
