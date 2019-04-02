package DAO.JPA;


import DAO.Interfaces.ReservationDAO;
import Domain.DinnerType;
import Domain.DinningTable;
import Domain.Reservation;
import Domain.TimeSlot;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReservationDAOJPA extends BaseDAOJPA<Reservation> implements ReservationDAO {

    @PersistenceContext(unitName = "nldPU")
    private EntityManager em;

    public ReservationDAOJPA() {
        super(Reservation.class);
    }

    @Override
    public ArrayList<Reservation> getReservations() {
        Query q = em.createQuery("SELECT r from Reservation r");
        return  new ArrayList<>(q.getResultList());
    }

    public Reservation addTable(Reservation r, DinningTable t){
        List<DinningTable> dinningTables = r.getDinningTables();
        dinningTables.add(t);
        r.setDinningTables(dinningTables);
        em.persist(r);
        return r;
    }

    public Reservation removeTable(Reservation r, DinningTable t){
        List<DinningTable> dinningTables = r.getDinningTables();
        dinningTables.remove(t);
        r.setDinningTables(dinningTables);
        em.persist(r);
        return r;
    }

    public Reservation addTimeSlots(Reservation r, TimeSlot ts){
        List<TimeSlot> timeSlots = r.getTimeSlots();
        timeSlots.add(ts);
        r.setTimeSlots(timeSlots);
        em.persist(r);
        return r;
    }

    public Reservation removeTimeSlots(Reservation r, TimeSlot ts){
        List<TimeSlot> timeSlots = r.getTimeSlots();
        timeSlots.remove(ts);
        r.setTimeSlots(timeSlots);
        em.persist(r);
        return  r;
    }

    public Reservation changeDinnerType(Reservation r, DinnerType dt){
        r.setType(dt);
        em.persist(r);
        return r;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
