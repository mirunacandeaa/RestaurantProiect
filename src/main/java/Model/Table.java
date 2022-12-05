package Model;

import java.util.List;

///class table
public class Table {


    private Integer TableId;
    private Integer nrPersons;
    private List<Waiter> waiterList;

    @Override
    public String toString(){
        return "TableId: " + getTableId();
    }

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


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Table.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Table other = (Table) obj;


        //If both lastnames are not equal return false
        if ((this.getTableId() == null) ? (other.getTableId() != null) : !this.getTableId().equals(other.getTableId())) {
            return false;
        }

        //If both lastnames are not equal return false
        if ((this.getWaiterList() == null) ? (other.getWaiterList() != null) : !this.getWaiterList().equals(other.getWaiterList())) {
            return false;
        }

        //If both lastnames are not equal return false
        if ((this.getNrPersons() == null) ? (other.getNrPersons() != null) : !this.getNrPersons().equals(other.getNrPersons())) {
            return false;
        }

        return true;
    }

}
