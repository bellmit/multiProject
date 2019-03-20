package Service;

import DAO.JPA;
import DAO.TableDAO;
import Domain.Table;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.UUID;
import javax.inject.Inject;


@Stateless
public class TableService {

    @Inject @JPA
    private TableDAO tableDAO;

    public void addTable(Table table) {
        tableDAO.addTable(table);
    }

    public void removeTable(Table table) {
        tableDAO.removeTable(table);
    }

    public ArrayList<Table> getTables() {
        return tableDAO.getTables();
    }

    public Table findById(UUID id) {
        return tableDAO.findById(id);
    }

    public void edit(Table table){
        tableDAO.editTables(table);
    }
    public TableService() {
    }
}
