package Service;



import DAO.Interfaces.TableDAO;
import Domain.DinningTable;

import javax.ejb.Stateless;
import java.util.ArrayList;
import javax.inject.Inject;


@Stateless
public class TableService {

    @Inject
    private TableDAO tableDAO;

    public void addTable(DinningTable dinningTable) {
        tableDAO.addTable(dinningTable);
    }

    public void removeTable(DinningTable dinningTable) {
        tableDAO.removeTable(dinningTable);
    }

    public ArrayList<DinningTable> getTables() {
        return tableDAO.getTables();
    }

    public DinningTable findById(String id) {
        return tableDAO.findById(id);
    }

    public void edit(DinningTable dinningTable){
        tableDAO.editTables(dinningTable);
    }
    public TableService() {
    }
}
