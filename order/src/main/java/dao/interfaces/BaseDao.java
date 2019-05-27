package dao.interfaces;

public interface BaseDao<T> {
    T create(T object);

    T find(String id);

    T edit(T object);

    void delete(T object);
}
