package Model;

import java.util.List;

public class Waiter extends Person{

    private Integer waiterID;
    private List<Table> tableList;

    public Waiter(String firstName, String lastName, Integer waiterID, List<Table> tableList) {
        super(firstName, lastName);
        this.waiterID = waiterID;
        this.tableList = tableList;
    }



    public Integer getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(Integer waiterID) {
        this.waiterID = waiterID;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }


}
