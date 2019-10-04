package models.common;

public interface CommonDaoInterface<T> {

    void insert(T object);

    void update(T object);

    T getById(int id);

    T getByName(String name);

    void deleteById(int id);

    void deleteByName(String name);

}
