package models.common;

public interface CommonDaoInterface<T> {

    void insert(T object);

    void update(T object);

    T getById(int id);

    void deleteById(int id);

}
