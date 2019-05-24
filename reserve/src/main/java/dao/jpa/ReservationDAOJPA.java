package dao.jpa;


import dao.Interfaces.ReservationDAO;
import domain.DinnerType;
import domain.DinningTable;
import domain.Reservation;
import domain.TimeSlot;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReservationDAOJPA extends BaseDaoJpa<Reservation> implements ReservationDAO {

    @PersistenceContext
    private EntityManager em;

    public ReservationDAOJPA() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> getReservations() {
        Query q = em.createQuery("SELECT r from Reservation r");
        return new ArrayList<>(q.getResultList());
    }

    public Reservation addTable(Reservation r, DinningTable t) {
        List<DinningTable> dinningTables = r.getDinningTables();
        dinningTables.add(t);
        r.setDinningTables(dinningTables);
        em.persist(r);
        return r;
    }

    public Reservation removeTable(Reservation r, DinningTable t) {
        List<DinningTable> dinningTables = r.getDinningTables();
        dinningTables.remove(t);
        r.setDinningTables(dinningTables);
        em.persist(r);
        return r;
    }

    public Reservation addTimeSlots(Reservation r, TimeSlot ts) {
        List<TimeSlot> timeSlots = r.getTimeSlots();
        timeSlots.add(ts);
        r.setTimeSlots(timeSlots);
        em.persist(r);
        return r;
    }

    public Reservation removeTimeSlots(Reservation r, TimeSlot ts) {
        List<TimeSlot> timeSlots = r.getTimeSlots();
        timeSlots.remove(ts);
        r.setTimeSlots(timeSlots);
        em.persist(r);
        return r;
    }

    public Reservation changeDinnerType(Reservation r, DinnerType dt) {
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
