package dao.jpa;

import config.props;
import dao.interfaces.BaseDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoJpa<T> implements BaseDao<T> {

    private Class<T> type;

    public BaseDaoJpa(Class<T> type){
        this.type = type;
    }

    @PersistenceContext(unitName = props.livePU)
    private EntityManager em;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(T object) {
        getEntityManager().persist(object);
    }

    @Override
    public T find(String id) {
        try {
            return getEntityManager().find(type, id);
        } catch (Exception ex) {
            return null;
        }
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
