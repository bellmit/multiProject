package dao.jpa;

import dao.interfaces.BaseDao;

import javax.persistence.EntityManager;

public abstract class BaseDaoJPA<T> implements BaseDao<T> {

    private Class<T> entityClass;

    public BaseDaoJPA(Class<T> type) {
        this.entityClass = type;
    }

    protected abstract EntityManager getEntityManager();

    @Override
    public T create(T object) {
        getEntityManager().persist(object);
        return object;
    }

    @Override
    public T find(String id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public T edit(T object) {
        getEntityManager().merge(object);
        return object;
    }

    @Override
    public void delete(T object) {
        getEntityManager().remove(object);
    }
}
