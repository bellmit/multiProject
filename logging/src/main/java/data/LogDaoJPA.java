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
        return null;
    }

    @Override
    public List<NLDLog> getForComponent(Component component) {
        return null;
    }

    @Override
    public List<NLDLog> getWithLevel(Level level) {
        return null;
    }

    @Override
    public List<NLDLog> getForComponentWithLevel(Component component, Level level) {
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public LogDaoJPA() {
        super(NLDLog.class);
    }
}
