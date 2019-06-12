package service;

import dao.interfaces.UserDao;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginPingService {

    @Inject
    UserDao userDao;

    public boolean ping() {
        userDao.delete(userDao.find(userDao.create(new User()).getId()));
        return true;
    }
}
