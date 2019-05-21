package DAO.Interfaces;


import Domain.DinningTable;
import dao.interfaces.BaseDao;

import java.util.ArrayList;

public interface TableDAO extends BaseDao<DinningTable> {
    ArrayList<DinningTable> getTables();
    ArrayList<DinningTable>  getAllAvailable();
}
