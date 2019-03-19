package DAO;

import Domain.Table;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.enterprise.inject.Default;


@Stateless @Default
public class TableDAOColl implements TableDAO{

    CopyOnWriteArrayList<Table> tables = new CopyOnWriteArrayList<>();


    @Override
    public void addTable(Table table) {
        tables.add(table);
    }

    @Override
    public void removeTable(Table table) {
        tables.remove(table);
    }

    @Override
    public Table findById(UUID id) {
        for (Table table : tables)
        {
            if(table.getId()==id){
                return table;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Table> getTables() {
        return new ArrayList<Table>(tables);
    }
}
