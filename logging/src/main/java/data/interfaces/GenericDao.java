package data.interfaces;

public interface GenericDao<T> {
    T create(T object);

    T find(String id);

    T edit(T object);

    void delete(T object);
}
