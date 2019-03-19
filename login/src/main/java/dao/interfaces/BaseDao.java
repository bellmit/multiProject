package dao.interfaces;

public interface BaseDao<T> {
    void create(T Object);
    T find(String id);
    void edit(T Object);
    void delete(T Object);
}
