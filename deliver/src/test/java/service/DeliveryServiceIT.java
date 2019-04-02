package service;

import data.DeliveryDaoJPA;
import data.interfaces.DeliveryDao;
import domain.Delivery;
import domain.Location;
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
import java.util.TreeSet;

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
        tx.commit();

        Assert.assertTrue(testOrderIds.containsAll(delivery.getOrderIdList()));
    }

    @Test
    public void getById() {
        tx.begin();
        Delivery delivery = addTestDelivery();
        Delivery toTest = deliveryService.getById(delivery.getDeliveryId());
        tx.commit();

        Assert.assertEquals(toTest.getDeliveryId(), delivery.getDeliveryId());
    }

    @Test
    public void addRoute() {
        tx.begin();
        Delivery delivery = addTestDelivery();
        Route route1 = new Route(new Location(0, 0), new Location(0, 0));
        Route route2 = new Route(new Location(50, 50), new Location(50,50));
        deliveryService.addRoute(delivery.getDeliveryId(), route1);
        Delivery toAssert = deliveryService.addRoute(delivery.getDeliveryId(), route2);
        tx.commit();

        Assert.assertTrue(toAssert.getRoutes().size() == 2);
    }

    @Test
    public void getRoutes() {
        tx.begin();
        Delivery delivery = addTestDelivery();
        Route route1 = new Route(new Location(0, 0), new Location(0, 0));
        deliveryService.addRoute(delivery.getDeliveryId(), route1);
        tx.commit();

        tx.begin();
        Set<Route> routes = deliveryService.getRoutes(delivery.getDeliveryId());
        Assert.assertTrue(routes.size() == 1);
    }

    @Test
    public void assignEmployee() {
        String employeeId = "TestEmployeeId";
        tx.begin();
        Delivery delivery = addTestDelivery();
        Delivery toAssert = deliveryService.assignEmployee(delivery.getDeliveryId(), employeeId);
        tx.commit();

        Assert.assertEquals(employeeId, toAssert.getEmployeeId());
    }

    @Test
    public void getAll() {
        tx.begin();
        addTestDelivery();
        List<Delivery> deliveries = deliveryService.getAll();
        tx.commit();
        Assert.assertEquals(1, deliveries.size());
    }

    @Test
    public void removeById() {
        tx.begin();
        Delivery delivery = addTestDelivery();
        deliveryService.removeById(delivery.getDeliveryId());
        tx.commit();
        tx.begin();
        Assert.assertNull(deliveryService.getById(delivery.getDeliveryId()));
        tx.commit();
    }

    @Test
    public void removeByEmployeeId() {
        String employeeId = "TestEmployeeId";
        tx.begin();
        Delivery delivery = addTestDelivery();
        deliveryService.assignEmployee(delivery.getDeliveryId(), employeeId);
        deliveryService.removeByEmployeeId(employeeId);
        tx.commit();
        tx.begin();
        Assert.assertEquals(0, deliveryService.getByEmployeeId(employeeId).size());
        tx.commit();
    }

    @Test
    public void editDelivery() {
        tx.begin();
        Delivery delivery = addTestDelivery();

        testOrderIds = new HashSet<>();
        testOrderIds.add("This");
        testOrderIds.add("edits");
        testOrderIds.add("a");
        testOrderIds.add("delivery");

        deliveryService.editDelivery(delivery.getDeliveryId(), testOrderIds);
        Delivery toAssert = deliveryService.getById(delivery.getDeliveryId());
        tx.commit();
        Assert.assertTrue(toAssert.getOrderIdList().containsAll(testOrderIds));
    }

    @Test
    public void getByEmployeeId() {
        String employeeId = "testEmployeeId";
        tx.begin();
        Delivery delivery = addTestDelivery();
        deliveryService.assignEmployee(delivery.getDeliveryId(), employeeId);
        List<Delivery> toTest = deliveryService.getByEmployeeId(employeeId);
        tx.commit();

        Assert.assertEquals(toTest.get(0).getDeliveryId(), delivery.getDeliveryId());
    }

}
