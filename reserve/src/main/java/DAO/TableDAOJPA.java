package DAO;


import Domain.Reservation;
import Domain.Table;

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
public class TableDAOJPA implements TableDAO {

    @PersistenceContext(unitName = "nldPU")
    private EntityManager em;

    @Override
    public void addTable(Table table) {
        em.persist(table);
    }

    @Override
    public void removeTable(Table table) {
        em.remove(table);
    }

    @Override
    public Table findById(String id) {
        Table t = em.find(Table.class,id);
        return t;

    }

    @Override
    public ArrayList<Table> getTables() {
        Query q = em.createQuery("SELECT t from Table t");
        return  new ArrayList<>(q.getResultList());
    }

    @Override
    public void editTables(Table t){
        em.merge(t);
    }

    public List<Table> getAllAvailable(){
        List<Table> allTables = getTables();
        ReservationDAOJPA reservationDAOJPA = new ReservationDAOJPA();
        List<Reservation> reservations = reservationDAOJPA.getReservations();
        List<Table> nonReservedTables = new ArrayList<>();
        for (Table t : allTables) {
            int count = 0;
            for (Reservation r: reservations) {
               if(!r.getTables().contains(t)){
                   count++;
               }
            }
            if(count==reservations.size()){
                nonReservedTables.add(t);
            }
        }
        return nonReservedTables;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
