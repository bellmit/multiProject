package util;

import domain.*;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

public class DatabaseCleaner {

    private final EntityManager em;
    private static final Class<?>[] ENTITY_TYPES = {
            Delivery.class,
            Location.class,
            Route.class,
            User.class,
            Role.class
    };

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    public void clean() {
        em.getTransaction().begin();
        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        em.getTransaction().commit();
        em.close();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("delete from " + getEntityName(entityType)).executeUpdate();
    }

    private String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }
}

