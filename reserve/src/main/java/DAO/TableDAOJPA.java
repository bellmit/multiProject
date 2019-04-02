package DAO;


import Domain.DinningTable;
import Domain.Reservation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@JPA
public class TableDAOJPA implements TableDAO {

    @PersistenceContext(unitName = "nldPU")
    private EntityManager em;

    @Override
    public void addTable(DinningTable dinningTable) {
        em.persist(dinningTable);
    }

    @Override
    public void removeTable(DinningTable dinningTable) {
        em.remove(dinningTable);
    }

    @Override
    public DinningTable findById(String id) {
        DinningTable t = em.find(DinningTable.class,id);
        return t;

    }

    @Override
    public ArrayList<DinningTable> getTables() {
        Query q = em.createQuery("SELECT t from DinningTable t");
        return  new ArrayList<>(q.getResultList());
    }

    @Override
    public void editTables(DinningTable t){
        em.merge(t);
    }

    public List<DinningTable> getAllAvailable(){
        List<DinningTable> allDinningTables = getTables();
        ReservationDAOJPA reservationDAOJPA = new ReservationDAOJPA();
        List<Reservation> reservations = reservationDAOJPA.getReservations();
        List<DinningTable> nonReservedDinningTables = new ArrayList<>();
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
}
