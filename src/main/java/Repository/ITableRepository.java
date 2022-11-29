package Repository;

import Model.Table;
import Model.Waiter;

import java.util.List;

public interface ITableRepository extends ICrudRepository<Integer, Table>{
    List<Waiter> getWaitersAtTable(Integer ID);
    void setWaiterListAtTable(Waiter waiter, Table table);
}
