package service;

import dao.interfaces.UserDao;
import domain.Component;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;

@Stateless
public class LoginPingService {

    @Inject
    private LogService logService;

    @Inject
    private UserDao userDao;

    public boolean ping() {
        userDao.delete(userDao.find(userDao.create(new User()).getId()));
        //sendLog();  // I THINK THIS DOES NOT WORK BECAUSE THE LOGGING MODULE DOES NOT HAVE ACCESS TO THE USER CLASS
                      // ALSO THE DATABASE DOES NOT SEEM TO LIKE THE DOUBLE CONNECTION
                      // FIX IS XA DATASOURCE VOOR ALLE WILDFLIES EN DATASOURCES
        return true;
    }
}
