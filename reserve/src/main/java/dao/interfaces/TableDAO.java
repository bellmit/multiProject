package dao.Interfaces;


import dao.interfaces.BaseDao;
import domain.DiningTable;

import java.util.List;

public interface TableDAO extends BaseDao<DiningTable> {
    List<DiningTable> getTables();

}
