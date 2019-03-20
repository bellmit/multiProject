package dao.jpa;

import dao.interfaces.BaseDao;

import javax.persistence.EntityManager;

public abstract class BaseDaoJpa<T> implements BaseDao<T> {

    public Class<T> entityClass;

    public BaseDaoJpa(Class<T> type) {
        this.entityClass = type;
    }

    protected abstract EntityManager getEntityManager();

    @Override
    public void create(T object) {
        getEntityManager().persist(object);
    }

    @Override
    public T find(String id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public void edit(T object) {
        getEntityManager().merge(object);
    }

    @Override
    public void delete(T object) {
        getEntityManager().remove(object);
    }
}
