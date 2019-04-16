package service;

import dao.interfaces.UserDao;
import domain.User;
import domain.dto.UserDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    UserDao userDao;

    public void create(User user) {
        userDao.create(user);
    }

    public void delete(String uuid) {
        User user = userDao.find(uuid);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        userDao.delete(user);
    }

    public User find(String uuid) {
        User user = userDao.find(uuid);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    public void edit(User user) {
        userDao.edit(user);
    }

    public User findByEmail(String email) {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    public List<UserDTO> getAll() {
        List<UserDTO> users = new ArrayList<>();
        for (User user : userDao.getAll()) {
            users.add(new UserDTO(user));
        }
        return users;
    }
}
