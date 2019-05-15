package dao.jpa;

import dao.interfaces.BaseDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoJpa<T> implements BaseDao<T> {
    @PersistenceContext
    public EntityManager em;

    private Class<T> type;

    public BaseDaoJpa(Class<T> type){
        this.type = type;
    }

    @Override
    public void create(T object) {
        em.persist(object);
    }

    @Override
    public T find(String id) {
        try {
            return em.find(type, id);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void edit(T object) {
        em.merge(object);
    }

    @Override
    public void delete(T object) {
        em.remove(object);
    }
}
