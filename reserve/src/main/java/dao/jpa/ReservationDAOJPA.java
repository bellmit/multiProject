package dao.jpa;


import dao.interfaces.ReservationDAO;
import domain.Reservation;
import domain.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class ReservationDAOJPA extends BaseDaoJpa<Reservation> implements ReservationDAO {

    @PersistenceContext(unitName = "reservePU")
    private EntityManager em;

    public ReservationDAOJPA() {
        super(Reservation.class);
    }


    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Reservation> getReservationsForDate(Date date) {
        TypedQuery<Reservation> query = em.createNamedQuery("reservation.findByDate", Reservation.class);
        query.setParameter("date", date);
        List<Reservation> reservations = query.getResultList();
        if (reservations != null && !reservations.isEmpty()) {
            return reservations;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Reservation> getAll() {
        Query q = em.createQuery("SELECT rs from Reservation rs");
        List<Reservation> reservations = new ArrayList<>();
        reservations.addAll(q.getResultList());
        return reservations;
    }
}
