package service;

import dao.interfaces.PingDao;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PingService {

    @Inject
    PingDao pingDao;

    public boolean ping() {
        return pingDao.testConnection() == 1;
    }
}