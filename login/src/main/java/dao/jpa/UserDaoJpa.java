package dao.jpa;

import dao.interfaces.UserDao;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDaoJpa extends BaseDaoJpa<User> implements UserDao {

    @PersistenceContext(unitName = "nldPU")
    private EntityManager em;

    public UserDaoJpa() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
