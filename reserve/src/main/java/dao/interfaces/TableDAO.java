package dao.interfaces;


import domain.DiningTable;

import java.util.List;

public interface TableDAO extends BaseDao<DiningTable> {
    List<DiningTable> getTables();

    List<DiningTable> getAllAvailable();
}
