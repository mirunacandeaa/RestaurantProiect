package Repository.inFile;

import Model.Waiter;
import Repository.IWaiterRepository;

import java.util.List;

public class InFileWaiterRepo implements IWaiterRepository {
    @Override
    public boolean add(Waiter waiter) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(Integer integer, Waiter waiter) {
        return false;
    }

    @Override
    public Waiter findbyId(Integer integer) {
        return null;
    }

    @Override
    public List<Waiter> getAll() {
        return null;
    }
}
