package domain.dto;

import domain.DiningTable;

public class DiningTableDTO {

    private String id;
    private int tableNumber;
    private int nrofSeats;

    public DiningTableDTO(DiningTable diningTable) {
        this.id = diningTable.getId();
        this.tableNumber = diningTable.getTableNumber();
        this.nrofSeats = diningTable.getNrofSeats();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNrofSeats() {
        return nrofSeats;
    }

    public void setNrofSeats(int nrofSeats) {
        this.nrofSeats = nrofSeats;
    }
}
