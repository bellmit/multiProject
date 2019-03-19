package dao.interfaces;

public interface BaseDao<T> {
    void create(T Object);
    T find(long id);
    void edit(T Object);
    void delete(T Object);
}
