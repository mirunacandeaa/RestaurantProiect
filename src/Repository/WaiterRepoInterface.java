package Repository;

import Model.Waiter;

import java.util.List;

public interface WaiterRepoInterface extends ICrudRepository<Integer, Waiter>{
    List<Waiter> getAllWaiters();
}
