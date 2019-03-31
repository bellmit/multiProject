package DAO;


import Domain.DinnerType;
import Domain.Reservation;
import Domain.Table;
import Domain.TimeSlot;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Stateless
@JPA
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

    public Reservation addTable(Reservation r, Table t){
        List<Table> tables = r.getTables();
        tables.add(t);
        r.setTables(tables);
        em.persist(r);
        return r;
    }

    public Reservation removeTable(Reservation r, Table t){
        List<Table> tables = r.getTables();
        tables.remove(t);
        r.setTables(tables);
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
