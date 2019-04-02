package DAO.Interfaces;


import Domain.DinningTable;

import java.util.ArrayList;

public interface TableDAO extends BaseDAO<DinningTable>  {

    ArrayList<DinningTable> getTables();

}
