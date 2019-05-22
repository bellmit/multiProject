package dao;

import dao.jpa.RoleDaoJpa;
import domain.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class RoleDaoJpaTest {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("nldTestPU");
    private EntityTransaction tx;
    private RoleDaoJpa roleDao;

    @Before
    public void setUp() {
        new DatabaseCleaner(emf.createEntityManager()).clean();
        EntityManager em = emf.createEntityManager();
        tx = em.getTransaction();

        roleDao = new RoleDaoJpa();
        roleDao.setEm(em);
    }

    @Test
    public void createAndFindRole() {
        tx.begin();
        Role role = new Role();
        roleDao.create(role);
        tx.commit();
        Assert.assertNotNull(roleDao.find(role.getId()));
    }

    @Test
    public void editRole() {
        tx.begin();
        Role role = new Role();
        roleDao.create(role);
        tx.commit();

        tx.begin();
        String name = "testRole";
        role.setName(name);
        roleDao.edit(role);
        tx.commit();

        Assert.assertEquals(roleDao.find(role.getId()).getName(), name);
    }

    @Test
    public void deleteRole() {
        tx.begin();
        Role role = new Role();
        roleDao.create(role);
        tx.commit();

        tx.begin();
        roleDao.delete(role);
        tx.commit();

        Assert.assertNull(roleDao.find(role.getId()));
    }
}
