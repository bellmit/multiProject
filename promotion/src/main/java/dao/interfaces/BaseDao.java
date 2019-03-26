package dao.interfaces;

import java.util.UUID;

public interface BaseDao<T> {
    void create(T Object);
    T find(String id);
    void edit(T Object);
    void delete(T Object);
}
