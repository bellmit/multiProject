package service;

import dao.interfaces.UserDao;
import domain.Role;
import domain.User;
import rest.auth.JWTHelper;
import util.RoleConverter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AuthService {

    @Inject
    UserDao userDao;

    @Inject
    JWTHelper jwtHelper;

    public String login(String email) {
        User foundUser = userDao.findByEmail(email);
        if (foundUser == null) {
            throw new NotFoundException("User not found");
        }
        List<String> userRoles = RoleConverter.roleArrayToStringArray(foundUser.getRoles());
        return jwtHelper.generatePrivateKey(foundUser.getEmail(), userRoles);
    }
}
