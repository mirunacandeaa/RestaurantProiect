package Model;

import java.util.List;

public class Table {


    private Integer TableId;
    private Integer nrPersons;
    private List<Waiter> waiterList;
    private TableAvalibility status;

    public Table(Integer tableId, Integer nrPersons, List<Waiter> waiterList, TableAvalibility status) {
        TableId = tableId;
        this.nrPersons = nrPersons;
        this.waiterList = waiterList;
        this.status=TableAvalibility.AVAILABLE;
    }

    public TableAvalibility getStatus() {
        return status;
    }

    public void setStatus(TableAvalibility status) {
        this.status = status;
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
