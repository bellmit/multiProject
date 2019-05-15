package data;

import data.interfaces.BaseDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDaoJPA<DO> implements BaseDao<DO> {

    private EntityManager em;
    private Class<DO> type;

    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.em = em;
    }

    public EntityManager getEm(){
        return em;
    }

    public BaseDaoJPA(Class<DO> type){
        this.type = type;
    }

    @Override
    public DO persist(DO dataObject) {
        em.persist(dataObject);
        return dataObject;
    }

    @Override
    public DO find(String id) {
        return em.find(type, id);
    }

    @Override
    public DO merge(DO object) {
        return em.merge(object);
    }

    @Override
    public void delete(DO object) {
        em.remove(object);
    }
}
