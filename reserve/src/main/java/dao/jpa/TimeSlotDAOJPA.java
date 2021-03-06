package dao.jpa;


import dao.interfaces.TimeSlotDAO;
import domain.TimeSlot;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TimeSlotDAOJPA extends BaseDaoJpa<TimeSlot> implements TimeSlotDAO {

    @PersistenceContext(unitName = "reservePU")
    private EntityManager em;

    public TimeSlotDAOJPA() {
        super(TimeSlot.class);
    }

    @Override
    public List<TimeSlot> getTimeSlots() {
        Query q = em.createQuery("SELECT ts from TimeSlot ts",TimeSlot.class);
        return q.getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
