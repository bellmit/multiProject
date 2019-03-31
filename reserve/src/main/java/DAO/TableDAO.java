package DAO;


import Domain.Table;

import java.util.ArrayList;
import java.util.UUID;

public interface TableDAO {

    void addTable(Table table);

    void removeTable (Table table);

    Table findById(String id);

    ArrayList<Table> getTables();

    void editTables(Table table);
}
