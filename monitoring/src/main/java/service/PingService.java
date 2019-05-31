package service;

import dao.interfaces.PingDao;
import domain.Ping;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PingService {

    @Inject
    PingDao pingDao;

    public boolean ping() {
        pingDao.delete(pingDao.create(new Ping()));
        return true;
    }
}
