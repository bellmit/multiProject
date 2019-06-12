package data.interfaces;

import domain.Component;
import domain.NldLog;

import java.util.List;
import java.util.logging.Level;

public interface LogDao extends GenericDao<NldLog> {
    List<NldLog> getAll();

    List<NldLog> getForComponent(Component component);

    List<NldLog> getWithLevel(Level level);

    List<NldLog> getForComponentWithLevel(Component component, Level level);
}
