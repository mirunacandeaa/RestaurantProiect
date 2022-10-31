package Model;

import java.util.List;

public class Table {


    private Integer TableId;
    private Integer nrPersons;
    private List<Waiter> waiterList;

    public Table(Integer tableId, Integer nrPersons, List<Waiter> waiterList) {
        TableId = tableId;
        this.nrPersons = nrPersons;
        this.waiterList = waiterList;
    }

    public Integer getTableId() {
        return TableId;
    }

    public void setTableId(Integer tableId) {
        TableId = tableId;
    }

    public Integer getNrPersons() {
        return nrPersons;
    }

    public void setNrPersons(Integer nrPersons) {
        this.nrPersons = nrPersons;
    }

    public List<Waiter> getWaiterList() {
        return waiterList;
    }

    public void setWaiterList(List<Waiter> waiterList) {
        this.waiterList = waiterList;
    }
}
