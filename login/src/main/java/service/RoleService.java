package service;

import dao.interfaces.RoleDao;
import domain.Role;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Stateless
public class RoleService {

    @Inject
    RoleDao roleDao;

    public void create(Role role) {
        roleDao.create(role);
    }

    public void delete(String uuid) {
        Role role = roleDao.find(uuid);
        if (role == null) {
            throw new WebApplicationException("Role not found", Response.Status.NOT_FOUND);
        }
        roleDao.delete(role);
    }

    public Role find(String uuid) {
        Role role = roleDao.find(uuid);
        if (role == null) {
            throw new WebApplicationException("Role not found", Response.Status.NOT_FOUND);
        }
        return role;
    }

    public void edit(Role role) {
        roleDao.edit(role);
    }
}
