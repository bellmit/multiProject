package DAO;

import Domain.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.UUID;

@Stateless
@JPA
public class ReservationDAOJPA implements ReservationDAO{

    @PersistenceContext(unitName = "reservationPU")
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
    public Reservation findById(UUID id) {
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
}
