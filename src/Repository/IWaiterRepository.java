package Repository;

import Model.Waiter;

import java.util.List;

public interface IWaiterRepository extends ICrudRepository<Integer, Waiter>{
    List<Waiter> getAllWaiters();
}
