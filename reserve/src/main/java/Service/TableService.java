package Service;


import DAO.Interfaces.TableDAO;
import Domain.DinningTable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;


@Stateless
public class TableService {

    @Inject
    private TableDAO tableDAO;

    public void addTable(DinningTable dinningTable) {
        if (dinningTable == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        tableDAO.create(dinningTable);
    }

    public void removeTable(DinningTable dinningTable) {
        if (dinningTable == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        tableDAO.delete(dinningTable);
    }

    public ArrayList<DinningTable> getTables() {
        ArrayList<DinningTable> dinningTables = tableDAO.getTables();
        if (dinningTables == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        return dinningTables;
    }

    public DinningTable findById(String id) {
        DinningTable table = tableDAO.find(id);
        if (table == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        return table;
    }

    public void edit(DinningTable dinningTable) {
        if (dinningTable == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        tableDAO.edit(dinningTable);
    }

    public TableService() {
    }

    public ArrayList<DinningTable> getAvailableTables() {
        ArrayList<DinningTable> availableDinningTables = tableDAO.getAllAvailable();
        if (availableDinningTables == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        return availableDinningTables;
    }
}
