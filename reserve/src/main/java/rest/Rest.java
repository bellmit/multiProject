package rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class Rest extends Application {
    public Rest() {
        /*BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("2.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/reservation/api");
        beanConfig.setResourcePackage("rest");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan();*/
    }
}
