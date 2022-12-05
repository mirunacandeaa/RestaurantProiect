package Repository.JDBA;

import Model.Table;
import Model.Waiter;
import Repository.IWaiterRepository;

import java.util.List;

public class JDBAWaiterRepo implements IWaiterRepository {
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

    @Override
    public List<Table> getTablesForWaiter(Integer ID) {
        return null;
    }

    @Override
    public void setTableForWaiter(Waiter waiter, Table table) {

    }
}
