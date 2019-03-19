package service;

import dao.interfaces.UserDao;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {

    @Inject
    UserDao userDao;

    public void create(User user) {
        userDao.create(user);
    }

    public void delete(String uuid) {
        userDao.delete(userDao.find(uuid));
    }

    public User find(String uuid) {
        return userDao.find(uuid);
    }

    public void edit(User user) {
        userDao.edit(user);
    }
}
