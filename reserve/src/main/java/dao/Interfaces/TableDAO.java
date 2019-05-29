package dao.Interfaces;


import dao.interfaces.BaseDao;
import domain.DinningTable;

import java.util.List;

public interface TableDAO extends BaseDao<DinningTable> {
    List<DinningTable> getTables();

}
