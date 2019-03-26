package DAO;


import Domain.Table;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.UUID;

@Stateless
@JPA
public class TableDAOJPA implements TableDAO {

    @PersistenceContext(unitName = "reservationPU")
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
    public Table findById(UUID id) {
        TypedQuery<Table> query = em.createNamedQuery("table.findById", Table.class);
        query.setParameter("id",id);
        return query.getSingleResult();

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

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
