package DAO.JPA;


import DAO.Interfaces.TimeSlotDAO;
import Domain.TimeSlot;
import dao.jpa.BaseDaoJpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

@Stateless
public class TimeSlotDAOJPA extends BaseDaoJpa<TimeSlot> implements TimeSlotDAO {

    @PersistenceContext
    private EntityManager em;

    public TimeSlotDAOJPA() {
        super(TimeSlot.class);
    }

    @Override
    public ArrayList<TimeSlot> getTimeSlots() {
        Query q = em.createQuery("SELECT ts from TimeSlot ts");
        return  new ArrayList<>(q.getResultList());
    }

    public void setEm(EntityManager em){
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
