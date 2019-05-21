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

    @PostConstruct
    private void intData() {
        User user = new User();
        user.setEmail("test@test.nl");
        User user2 = new User();
        user2.setEmail("test@tests.nl");
        Role role = new Role();
        role.setName("user");
        Role role2 = new Role();
        role2.setName("admin");
        Role role3 = new Role();
        role3.setName("waiter");
        user.getRoles().add(role);
        user2.getRoles().add(role);

        roleDao.create(role);
        roleDao.create(role2);
        roleDao.create(role3);

        userDoa.create(user);
        userDoa.create(user2);
    }
}
