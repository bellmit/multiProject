package data;

import dao.jpa.BaseDaoJpa;
import data.interfaces.LogDao;
import domain.Component;
import domain.NLDLog;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;

@Stateless
public class LogDaoJPA extends BaseDaoJpa<NLDLog> implements LogDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<NLDLog> getAll() {
        return getEntityManager().createQuery("SELECT L FROM NLDLog L", NLDLog.class)
                .getResultList();
    }

    @Override
    public List<NLDLog> getForComponent(Component component) {
        return getEntityManager().createQuery("SELECT L FROM NLDLog L WHERE l.component = :component", NLDLog.class)
                .setParameter("component", component)
                .getResultList();
    }

    @Override
    public List<NLDLog> getWithLevel(Level level) {
        return getEntityManager().createQuery("SELECT L FROM NLDLog L WHERE l.level = :level", NLDLog.class)
                .setParameter("level", level)
                .getResultList();
    }

    @Override
    public List<NLDLog> getForComponentWithLevel(Component component, Level level) {
        return getEntityManager().createQuery("SELECT L FROM NLDLog L WHERE l.component = :component AND l.level = :level", NLDLog.class)
                .setParameter("component", component)
                .setParameter("level", level)
                .getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public LogDaoJPA() {
        super(NLDLog.class);
    }
}
