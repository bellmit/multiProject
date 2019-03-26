package service;

import dao.interfaces.UserDao;
import domain.User;
import exceptions.UserNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {

    @Inject
    UserDao userDao;

    public void create(User user) {
        userDao.create(user);
    }

    public void delete(String uuid) throws UserNotFoundException {
        User user = userDao.find(uuid);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        userDao.delete(user);
    }

    public User find(String uuid) throws UserNotFoundException {
        User user = userDao.find(uuid);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    public void edit(User user) {
        userDao.edit(user);
    }
}
