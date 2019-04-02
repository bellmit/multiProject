package DAO;


import DAO.Interfaces.TimeSlotDAO;
import Domain.TimeSlot;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

@Stateless
public class TimeSlotDAOJPA implements TimeSlotDAO {

    @PersistenceContext(unitName = "nldPU")
    private EntityManager em;

    @Override
    public void addTimeSlot(TimeSlot ts) {
        em.persist(ts);
    }

    @Override
    public void removeTimeSlot(TimeSlot ts) {
        em.remove(ts);
    }

    @Override
    public TimeSlot findById(String id) {
        TypedQuery<TimeSlot> query = em.createNamedQuery("timeslot.findById", TimeSlot.class);
        query.setParameter("id",id);
        return query.getSingleResult();

    }

    @Override
    public ArrayList<TimeSlot> getTimeSlots() {
        Query q = em.createQuery("SELECT ts from TimeSlot ts");
        return  new ArrayList<>(q.getResultList());
    }

    @Override
    public void editTimeSlots(TimeSlot ts){
        em.merge(ts);
    }

    public void setEm(EntityManager em){
        this.em = em;
    }
}
