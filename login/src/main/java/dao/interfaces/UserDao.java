package dao.interfaces;

import domain.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    User findByEmail(String email);
    List<User> getAll();
}
