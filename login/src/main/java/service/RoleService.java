package service;

import dao.interfaces.RoleDao;
import domain.Role;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RoleService {

    @Inject
    RoleDao roleDao;

    public void create(Role role) {
        roleDao.create(role);
    }

    public void delete(String uuid) {
        roleDao.delete(roleDao.find(uuid));
    }

    public Role find(String uuid) {
        return roleDao.find(uuid);
    }

    public void edit(Role role) {
        roleDao.edit(role);
    }
}
