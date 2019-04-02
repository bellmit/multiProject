package service;

import data.DeliveryDaoJPA;
import data.interfaces.DeliveryDao;
import domain.Delivery;
import domain.Route;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeliveryServiceIT {

    private DeliveryService deliveryService;

    private EntityManager em;
    private EntityTransaction tx;

    private Set<String> testOrderIds;

    @Before
    public void setUp() {
        //setup the test database and transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        em = emf.createEntityManager();
        tx = em.getTransaction();

        //clean the database off previous test contamination (different entityManager necessary)
        new DatabaseCleaner(emf.createEntityManager()).clean();

        deliveryService = new DeliveryService();
        DeliveryDaoJPA deliveryDao = new DeliveryDaoJPA();
        deliveryDao.setEntityManager(em);
        deliveryService.setDeliveryDao(deliveryDao);

        testOrderIds = new HashSet<>();
        testOrderIds.add("This");
        testOrderIds.add("is");
        testOrderIds.add("a");
        testOrderIds.add("test");

    }

    private Delivery addTestDelivery(){
        return deliveryService.addDelivery(testOrderIds);
    }

    @After
    public void setDown() {
        em.close();
    }

    @Test
    public void addDelivery() {
        tx.begin();
        Delivery delivery = addTestDelivery();
        Assert.assertTrue(testOrderIds.containsAll(delivery.getOrderIdList()));
        tx.commit();
    }

    @Test
    public void getById() {
        tx.begin();
        Delivery delivery = addTestDelivery();
        Delivery toTest = deliveryService.getById(delivery.getDeliveryId());
        Assert.assertEquals(delivery.getDeliveryId(), toTest.getDeliveryId());
    }

    @Test
    public void addRoute() {

    }

    @Test
    public void getRoutes() {

    }

    @Test
    public void assignEmployee() {
    }

    @Test
    public void getAll() {

    }

    @Test
    public void removeById() {

    }

    @Test
    public void removeByEmployeeId() {
    }

    @Test
    public void editDelivery() {
    }

    @Test
    public void getByEmployeeId() {

    }

}
