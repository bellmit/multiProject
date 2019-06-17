package service;


import dao.interfaces.TableDAO;
import domain.DiningTable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class TableService {

    @Inject
    private TableDAO tableDAO;

    public void addTable(DiningTable diningTable) {
        if (diningTable == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        tableDAO.create(diningTable);
    }

    public void removeTable(DiningTable diningTable) {
        if (diningTable == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        tableDAO.delete(diningTable);
    }

    public List<DiningTable> getTables() {
        return tableDAO.getTables();
    }

    public DiningTable findById(String id) {
        DiningTable table = tableDAO.find(id);
        if (table == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        return table;
    }

    public void edit(DiningTable diningTable) {
        if (diningTable == null) {
            throw new javax.ws.rs.NotFoundException();
        }
        tableDAO.edit(diningTable);
    }

}
