package util;

import domain.Role;
import domain.User;
import service.AuthService;
import service.RoleService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Startup
@Singleton
public class InitData2 {

    @Inject
    UserService us;

    @Inject
    RoleService rs;

    @Inject
    AuthService as;

    @PostConstruct
    public void init() {
        //create roles
        Role r = new Role();
        r.setName("admin");
        rs.create(r);
        List<Role> roles = new ArrayList<>();
        roles.add(r);

        Role r2 = new Role();
        r2.setName("table");
        rs.create(r2);
        List<Role> roles2 = new ArrayList<>();
        roles2.add(r2);

        Role r3 = new Role();
        r3.setName("employee");
        rs.create(r3);
        List<Role> roles3 = new ArrayList<>();
        roles3.add(r3);

        Role r4 = new Role();
        r4.setName("user");
        rs.create(r4);
        List<Role> roles4 = new ArrayList<>();
        roles4.add(r4);

        //create testUser
        User u = new User();
        u.setEmail("admin@fontys.nl");
        u.setPassword("admin");
        u.setRoles(roles);
        //us.create(u);
        as.addUser(u);

        //create testUser
        User u2 = new User();
        u2.setEmail("table@fontys.nl");
        u2.setPassword("table");
        u2.setRoles(roles2);
        //us.create(u2);
        as.addUser(u2);

        //create testUser
        User u3 = new User();
        u3.setEmail("emp@fontys.nl");
        u3.setPassword("emp");
        u3.setRoles(roles3);
        //us.create(u3);
        as.addUser(u3);

        //create testUser
        User u4 = new User();
        u4.setEmail("user@fontys.nl");
        u4.setPassword("user");
        u4.setRoles(roles4);
        //us.create(u4);
        as.addUser(u4);
    }

}
