package data.interfaces;

public interface BaseDao<DO> {
    DO persist(DO object);
    DO find(String id);
    DO merge(DO object);
    void delete(DO object);
}
