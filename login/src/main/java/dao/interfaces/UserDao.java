package dao.interfaces;

import domain.User;

public interface UserDao extends BaseDao<User> {
    User findByEmail(String email);
}
