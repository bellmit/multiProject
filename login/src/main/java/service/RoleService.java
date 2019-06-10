package service;

import dao.interfaces.RoleDao;
import domain.Role;
import util.RoleConverter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
public class RoleService {

    @Inject
    RoleDao roleDao;

    public void create(Role role) {
        Role foundRole = roleDao.findByName(role.getName());
        if (foundRole == null) {
            roleDao.create(role);
        } else {
            throw new BadRequestException("Role already exists");
        }
    }

    public void delete(String uuid) {
        Role role = roleDao.find(uuid);
        if (role == null) {
            throw new NotFoundException("User not found");
        }
        roleDao.delete(role);
    }

    public Role find(String uuid) {
        Role role = roleDao.find(uuid);
        if (role == null) {
            throw new NotFoundException("User not found");
        }
        return role;
    }

    public void edit(Role role) {
        roleDao.edit(role);
    }

    public List<String> getAll() {
        return RoleConverter.roleArrayToStringArray(roleDao.getAll());
    }
}
