package util;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class initData {
    @PostConstruct
    public void init(){

    }

}
