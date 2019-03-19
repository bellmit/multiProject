package dao.implementation;

import dao.interfaces.UserDao;
import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
