package util;

import domain.Reservation;
import domain.DinningTable;
import domain.TimeSlot;

import javax.persistence.*;
import javax.persistence.metamodel.*;
import java.sql.*;

public class DatabaseCleaner {

    private static final Class<?>[] ENTITY_TYPES = {
            Reservation.class,
            DinningTable.class,
            TimeSlot.class
    };
    private final EntityManager em;

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    public void clean() throws SQLException {
        em.getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        em.getTransaction().commit();
        em.close();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("DELETE from " + getEntityName(entityType)).executeUpdate();
    }

    protected String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }
}
