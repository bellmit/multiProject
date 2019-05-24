package dao.Interfaces;


import domain.DinningTable;
import dao.interfaces.BaseDao;

import java.util.List;

public interface TableDAO extends BaseDao<DinningTable> {
    List<DinningTable> getTables();

    List<DinningTable> getAllAvailable();
}