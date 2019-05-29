package dao.jpa;


import dao.Interfaces.TableDAO;
import domain.DinningTable;
import domain.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TableDAOJPA extends BaseDaoJpa<DinningTable> implements TableDAO {

    @PersistenceContext
    private EntityManager em;

    public TableDAOJPA() {
        super(DinningTable.class);
    }

    @Override
    public List<DinningTable> getTables() {
        Query q = em.createQuery("SELECT t from DinningTable t");
        return  new ArrayList<>(q.getResultList());
    }


    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
