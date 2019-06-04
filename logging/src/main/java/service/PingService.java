package service;

import data.interfaces.LogDao;
import domain.NldLog;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PingService {

    @Inject
    LogDao logDao;

    public boolean ping() {
        logDao.delete(logDao.find(logDao.create(new NldLog()).getUuid()));
        return true;
    }
}
