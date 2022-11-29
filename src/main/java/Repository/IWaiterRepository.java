package Repository;

import Model.Table;
import Model.Waiter;

import java.util.List;

public interface IWaiterRepository extends ICrudRepository<Integer, Waiter>{
    List<Table> getTablesForWaiter(Integer ID);
    void setTableForWaiter(Waiter waiter, Table table);
}
