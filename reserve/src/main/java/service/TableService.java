package service;


import dao.Interfaces.TableDAO;
import domain.DinningTable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


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

    public List<DinningTable> getTables() {
        List<DinningTable> dinningTables = tableDAO.getTables();
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

}
