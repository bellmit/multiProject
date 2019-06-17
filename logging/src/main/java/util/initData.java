package util;

import domain.Component;
import service.LogService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Level;

@Singleton
//@Startup
public class initData {

    @Inject
    private LogService logService;

    @PostConstruct
    public void init(){
        // initLogs();
    }

    private void initLogs(){
        System.out.println("initializing Logs: ");
        logService.addLog(Component.DELIVER, this.getClass().getName(), "this is a test log from DELIVER.initData", Level.FINE);
        logService.addLog(Component.LOGIN, this.getClass().getName(), "this is test log from LOGIN.initData", Level.WARNING);
        logService.addLog(Component.ORDER, this.getClass().getName(), "this is a test log from ORDER.initData", Level.INFO);
    }

}
