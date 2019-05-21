package dao.jpa;

import dao.interfaces.RoleDao;
import domain.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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

    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = em.createNamedQuery("role.findByName", Role.class);
        query.setParameter("name", name);
        List<Role> roles = query.getResultList();
        if (roles != null && !roles.isEmpty()) {
            return roles.get(0);
        }
        return null;
    }

    @Override
    public List<Role> getAll() {
        return em.createQuery("SELECT r FROM Role r").getResultList();
    }
}
