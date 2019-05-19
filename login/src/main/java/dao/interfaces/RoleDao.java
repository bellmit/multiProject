package dao.interfaces;

import domain.Role;

import java.util.List;

public interface RoleDao extends BaseDao<Role> {
    Role findByName(String name);
    List<Role> getAll();
}
