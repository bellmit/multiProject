package service;

import dao.interfaces.RoleDao;
import domain.Role;
import exceptions.RoleNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RoleService {

    @Inject
    RoleDao roleDao;

    public void create(Role role) {
        roleDao.create(role);
    }

    public void delete(String uuid) throws RoleNotFoundException {
        Role role = roleDao.find(uuid);
        if (role == null) {
            throw new RoleNotFoundException("Role not found");
        }
        roleDao.delete(role);
    }

    public Role find(String uuid) throws RoleNotFoundException {
        Role role = roleDao.find(uuid);
        if (role == null) {
            throw new RoleNotFoundException("Role not found");
        }
        return role;
    }

    public void edit(Role role) {
        roleDao.edit(role);
    }
}
