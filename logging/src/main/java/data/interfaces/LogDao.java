package data.interfaces;

import dao.interfaces.BaseDao;
import domain.Component;
import domain.NLDLog;

import java.util.List;
import java.util.logging.Level;

public interface LogDao extends BaseDao<NLDLog> {
    List<NLDLog> getAll();

    List<NLDLog> getForComponent(Component component);

    List<NLDLog> getWithLevel(Level level);

    List<NLDLog> getForComponentWithLevel(Component component, Level level);
}
