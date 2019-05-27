package service;

import data.interfaces.LogDao;
import domain.Component;
import domain.NLDLog;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class LogService {
    @Inject
    LogDao logDao;

    public NLDLog addLog(Component component, Class className, String message, Level level){
        NLDLog log = new NLDLog();
        log.setComponent(component);
        log.setMessage(message);
        log.setLevel(level);
        return log(log);
    }

    public List<NLDLog> getAllLogs(){
        return logDao.getAll();
    }

    public List<NLDLog> getLogsForComponent(Component component){
        return logDao.getForComponent(component);
    }

    public List<NLDLog> getLogsWithLevel(Level level) {
        return logDao.getWithLevel(level);
    }

    public List<NLDLog> getLogsForComponentWithLevel(Component component, Level level){
        return logDao.getForComponentWithLevel(component, level);
    }

    private NLDLog log(NLDLog log){
        Logger.getLogger(log.getComponent().toString()).log(log.getLevel(), log.getMessage());
        Logger.getLogger(log.getComponent().toString() +"."+ log.getClassName()).log(log.getLevel(), log.getMessage());
        return logDao.create(log);
    }

}
