package config;

import dao.interfaces.RoleDao;
import dao.interfaces.UserDao;
import domain.Role;
import domain.User;

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

    public Startup() {
    }

    @PostConstruct
    private void intData() {
        User user = new User();
        user.setEmail("test@test.nl");
        Role role = new Role();
        role.setName("user");
        user.getRoles().add(role);

        roleDao.create(role);
        userDoa.create(user);
    }
}
