package Repository;

import Model.Waiter;

import java.util.List;

public interface WaiterRepoInterface extends ICrudRepository<String, Waiter>{
    List<Waiter> getAllWaiters();
}
