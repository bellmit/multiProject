package util;

import domain.Component;
import domain.NLDLog;
import service.LogService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Level;

@Singleton
@Startup
public class initData {

    @Inject
    private LogService logService;

    @PostConstruct
    public void init(){
        // initLogs();
    }

    private void initLogs(){
        System.out.println("initializing Logs: ");
        logService.addLog(Component.deliver, this.getClass().getName(), "this is a test log from deliver.initData", Level.FINE);
        logService.addLog(Component.login, this.getClass().getName(), "this is test log from login.initData", Level.WARNING);
        logService.addLog(Component.order, this.getClass().getName(), "this is a test log from order.initData", Level.INFO);
    }

}
