package dao.implementation;

import dao.interfaces.RoleDao;
import domain.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
