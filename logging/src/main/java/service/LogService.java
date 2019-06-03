package service;

import data.interfaces.LogDao;
import domain.Component;
import domain.NldLog;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class LogService {
    @Inject
    LogDao logDao;

    public NldLog addLog(Component component, String className, String message, Level level){
        NldLog log = new NldLog();
        log.setComponent(component);
        log.setMessage(message);
        log.setClassName(className);
        log.setLevel(level);
        return log(log);
    }

    public List<NldLog> getAllLogs(){
        return logDao.getAll();
    }

    public List<NldLog> getLogsForComponent(Component component){
        return logDao.getForComponent(component);
    }

    public List<NldLog> getLogsWithLevel(Level level) {
        return logDao.getWithLevel(level);
    }

    public List<NldLog> getLogsForComponentWithLevel(Component component, Level level){
        return logDao.getForComponentWithLevel(component, level);
    }

    private NldLog log(NldLog log){
        Logger.getLogger(log.getComponent().toString() +"."+ log.getClassName()).log(log.getLevel(), log.getMessage());
        return logDao.create(log);
    }

    public NldLog addTestLog(String message) {
        return this.log(new NldLog(Component.logging, this.getClass().getName(), "The test log says: "+message, Level.ALL));
    }
}
