package Repository;

import Model.Table;
import Model.Waiter;

import java.util.List;

///interface for tables extended from the main interface
public interface ITableRepository extends ICrudRepository<Integer, Table>{
    ///specific method that returns the waiterlist of a table
    List<Waiter> getWaitersAtTable(Integer ID);
    ///specific method that adds a waiter to the waiterlist of a table
    void setWaiterListAtTable(Waiter waiter, Table table);
}
