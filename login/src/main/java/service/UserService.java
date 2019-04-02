package service;

import dao.interfaces.UserDao;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Stateless
public class UserService {

    @Inject
    UserDao userDao;

    public void create(User user) {
        userDao.create(user);
    }

    public void delete(String uuid) {
        User user = userDao.find(uuid);
        if (user == null) {
            throw new WebApplicationException("User not found", Response.Status.NOT_FOUND);
        }
        userDao.delete(user);
    }

    public User find(String uuid) {
        User user = userDao.find(uuid);
        if (user == null) {
            throw new WebApplicationException("User not found", Response.Status.NOT_FOUND);
        }
        return user;
    }

    public void edit(User user) {
        userDao.edit(user);
    }

    public User findByEmail(String email) {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new WebApplicationException("User not found", Response.Status.NOT_FOUND);
        }
        return user;
    }
}
