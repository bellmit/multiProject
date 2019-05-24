package dao.jpa;


import dao.Interfaces.TableDAO;
import domain.DinningTable;
import domain.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TableDAOJPA extends BaseDaoJpa<DinningTable> implements TableDAO {

    @PersistenceContext
    private EntityManager em;

    public TableDAOJPA() {
        super(DinningTable.class);
    }

    @Override
    public List<DinningTable> getTables() {
        Query q = em.createQuery("SELECT t from DinningTable t");
        return  new ArrayList<>(q.getResultList());
    }


    @Override
    public List<DinningTable> getAllAvailable(){
        List<DinningTable> allDinningTables = getTables();
        ReservationDAOJPA reservationDAOJPA = new ReservationDAOJPA();
        List<Reservation> reservations = reservationDAOJPA.getReservations();
        ArrayList<DinningTable> nonReservedDinningTables = new ArrayList<>();
        for (DinningTable t : allDinningTables) {
            int count = 0;
            for (Reservation r: reservations) {
               if(!r.getDinningTables().contains(t)){
                   count++;
               }
            }
            if(count==reservations.size()){
                nonReservedDinningTables.add(t);
            }
        }
        return nonReservedDinningTables;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}