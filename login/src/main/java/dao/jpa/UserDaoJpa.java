package dao.jpa;

import dao.interfaces.UserDao;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = em.createNamedQuery("user.findByEmail", User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
