package config;

import dao.interfaces.RoleDao;
import dao.interfaces.UserDao;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
@javax.ejb.Startup
public class Startup {
    @Inject
    UserDao userDoa;

    @Inject
    RoleDao roleDao;

    @PostConstruct
    private void intData() {
    }
}
