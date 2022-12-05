package Repository;

import Model.Table;
import Model.Waiter;

import java.util.List;

///interface for waiters extended from the main interface
public interface IWaiterRepository extends ICrudRepository<Integer, Waiter>{
    ///specific method that returns the tablelist of a waiter
    List<Table> getTablesForWaiter(Integer ID);
    ///specific method that add a table to the tablelist of a waiter
    void setTableForWaiter(Waiter waiter, Table table);
}
