package service;

import dao.PingDao;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PingService {

    @Inject
    PingDao pingDao;

    public boolean hasDatabaseConnection() {
        return pingDao.hasDatabaseConnection();
    }
}
