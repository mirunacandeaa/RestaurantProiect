package Repository.JDBA;

import Model.Table;
import Model.Waiter;
import Repository.ITableRepository;

import java.util.List;

public class JDBATableRepo implements ITableRepository {
    @Override
    public boolean add(Table table) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(Integer integer, Table table) {
        return false;
    }

    @Override
    public Table findbyId(Integer integer) {
        return null;
    }

    @Override
    public List<Table> getAll() {
        return null;
    }

    @Override
    public List<Waiter> getWaitersAtTable(Integer ID) {
        return null;
    }

    @Override
    public void setWaiterListAtTable(Waiter waiter, Table table) {

    }
}
