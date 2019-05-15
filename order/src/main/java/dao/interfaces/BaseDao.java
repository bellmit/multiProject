package dao.interfaces;

public interface BaseDao<T> {
    void create(T object);
    T find(String id);
    void edit(T object);
    void delete(T object);
}
