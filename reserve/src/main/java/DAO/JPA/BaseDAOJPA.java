package DAO.JPA;

import DAO.Interfaces.BaseDAO;

import javax.persistence.EntityManager;

public abstract class BaseDAOJPA<T> implements BaseDAO<T> {

    public Class<T> entityClass;

    public BaseDAOJPA(Class<T> type) {
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
