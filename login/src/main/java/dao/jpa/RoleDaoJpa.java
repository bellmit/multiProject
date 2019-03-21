package dao.jpa;

import dao.interfaces.RoleDao;
import domain.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RoleDaoJpa extends BaseDaoJpa<Role> implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    public RoleDaoJpa() {
        super(Role.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
