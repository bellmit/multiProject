package dao.implementation;

import dao.interfaces.BaseDao;

import javax.persistence.EntityManager;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    public Class<T> entityClass;

    public BaseDaoImpl(Class<T> type) {
        this.entityClass = type;
    }

    protected abstract EntityManager getEntityManager();

    @Override
    public void create(T object) {
        getEntityManager().persist(object);
    }

    @Override
    public T find(long id) {
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
