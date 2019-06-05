package data;

import dao.jpa.BaseDaoJpa;
import data.interfaces.LogDao;
import domain.Component;
import domain.NldLog;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;

@Stateless
public class LogDaoJPA extends BaseDaoJpa<NldLog> implements LogDao {

    @PersistenceContext(unitName = "nldPU")
    private EntityManager em;

    @Override
    public List<NldLog> getAll() {
        return getEntityManager().createQuery("SELECT L FROM NldLog L", NldLog.class)
                .getResultList();
    }

    @Override
    public List<NldLog> getForComponent(Component component) {
        return getEntityManager().createQuery("SELECT L FROM NldLog L WHERE l.component = :component", NldLog.class)
                .setParameter("component", component)
                .getResultList();
    }

    @Override
    public List<NldLog> getWithLevel(Level level) {
        return getEntityManager().createQuery("SELECT L FROM NldLog L WHERE l.level = :level", NldLog.class)
                .setParameter("level", level)
                .getResultList();
    }

    @Override
    public List<NldLog> getForComponentWithLevel(Component component, Level level) {
        return getEntityManager().createQuery("SELECT L FROM NldLog L WHERE l.component = :component AND l.level = :level", NldLog.class)
                .setParameter("component", component)
                .setParameter("level", level)
                .getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public LogDaoJPA() {
        super(NldLog.class);
    }
}
