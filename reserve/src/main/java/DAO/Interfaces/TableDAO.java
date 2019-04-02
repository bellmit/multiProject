package DAO;


import Domain.DinningTable;

import java.util.ArrayList;

public interface TableDAO {

    void addTable(DinningTable dinningTable);

    void removeTable (DinningTable dinningTable);

    DinningTable findById(String id);

    ArrayList<DinningTable> getTables();

    void editTables(DinningTable dinningTable);
}
