package dao;

import dao.jpa.BaseDaoJpa;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PingDao extends BaseDaoJpa<Object> {

    @PersistenceContext
    private EntityManager em;

    public PingDao() {
        super(Object.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public boolean hasDatabaseConnection() {
        return getEntityManager() != null;
    }

}
