package dao.jpa;


import dao.interfaces.TableDAO;
import domain.DiningTable;
import domain.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TableDAOJPA extends BaseDaoJpa<DiningTable> implements TableDAO {

    @PersistenceContext(unitName = "reservePU")
    private EntityManager em;

    public TableDAOJPA() {
        super(DiningTable.class);
    }

    @Override
    public List<DiningTable> getTables() {
        Query q = em.createQuery("SELECT t from DinningTable t");
        return new ArrayList<>(q.getResultList());
    }


    @Override
    public List<DiningTable> getAllAvailable() {
        List<DiningTable> allDiningTables = getTables();
        ReservationDAOJPA reservationDAOJPA = new ReservationDAOJPA();
        List<Reservation> reservations = reservationDAOJPA.getReservations();
        ArrayList<DiningTable> nonReservedDiningTables = new ArrayList<>();
        for (DiningTable t : allDiningTables) {
            int count = 0;
            for (Reservation r : reservations) {
                if (!r.getDiningTables().contains(t)) {
                    count++;
                }
            }
            if (count == reservations.size()) {
                nonReservedDiningTables.add(t);
            }
        }
        return nonReservedDiningTables;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
