package Repository.inMemory;

import Model.Table;
import Repository.ITableRepository;

import java.util.List;

public class InMemoryTableRepo implements ITableRepository {
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
}
