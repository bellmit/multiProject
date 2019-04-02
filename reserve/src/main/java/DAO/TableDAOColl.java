package main.java.DAO;


import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.enterprise.inject.Default;


@Stateless @Default
public class TableDAOColl {//implements TableDAO{

    /*
    CopyOnWriteArrayList<DinningTable> tables = new CopyOnWriteArrayList<>();


    @Override
    public void addTable(DinningTable table) {
        tables.add(table);
    }

    @Override
    public void removeTable(DinningTable table) {
        tables.remove(table);
    }

    @Override
    public DinningTable findById(UUID id) {
        for (DinningTable table : tables)
        {
            if(table.getId()==id){
                return table;
            }
        }
        return null;
    }

    @Override
    public ArrayList<DinningTable> getDinningTables() {
        return new ArrayList<DinningTable>(tables);
    }
    */
}
