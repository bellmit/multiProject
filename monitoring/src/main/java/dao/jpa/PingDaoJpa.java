package dao.jpa;

import dao.interfaces.PingDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PingDaoJpa implements PingDao {

    @PersistenceContext
    private EntityManager em;

    public PingDaoJpa() {
    }

    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public int testConnection() {
        return (int) getEntityManager().createNativeQuery("SELECT 1 FROM dual").getSingleResult();
    }


}
