package DAO;


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
public class ReservationDAOJPA implements ReservationDAO {

    @PersistenceContext(unitName = "nldPU")
    private EntityManager em;

    @Override
    public void addReservation(Reservation reservation) {
        em.persist(reservation);
    }

    @Override
    public void removeReservation(Reservation reservation) {
        em.remove(reservation);
    }

    @Override
    public Reservation findById(String id) {
        TypedQuery<Reservation> query = em.createNamedQuery("reservation.findById", Reservation.class);
        query.setParameter("id",id);
        return query.getSingleResult();

    }

    @Override
    public ArrayList<Reservation> getReservations() {
        Query q = em.createQuery("SELECT r from Reservation r");
        return  new ArrayList<>(q.getResultList());
    }

    @Override
    public void editReservations(Reservation r){
        em.merge(r);
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
}
