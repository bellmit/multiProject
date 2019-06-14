package dao.jpa;


import dao.interfaces.ReservationDAO;
import domain.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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
    public List<Reservation> getAll() {
        Query q = em.createQuery("SELECT rs from Reservation rs");
        List<Reservation> reservations = new ArrayList<>();
        reservations.addAll(q.getResultList());
        return reservations;
    }
}
