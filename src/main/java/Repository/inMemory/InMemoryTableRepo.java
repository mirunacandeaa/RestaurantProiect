package Repository.inMemory;

import Model.Table;
import Model.Waiter;
import Repository.ITableRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTableRepo implements ITableRepository {
    private List<Table> tableList = new ArrayList<>();

    public void populate(){
        Table table1 = new Table(1, 4, null);
        Table table2 = new Table(2, 2, null);
        Table table3 = new Table(3, 10, null);
        tableList.add(table1);
        tableList.add(table2);
        tableList.add(table3);
    }

    @Override
    public boolean add(Table table) {
            for(Table c : tableList)
                if(c.equals(table))
                    return false;
            tableList.add(table);
            return true;
    }

    @Override
    public boolean delete(Integer ID) {
        for(Table c : tableList){
            if(c.getTableId().equals(ID)){
                tableList.remove(c);
                return true;
            }}
        return false;
    }

    @Override
    public boolean update(Integer ID, Table table) {
        for(Table c : tableList){
            if(c.getTableId().equals(ID)){
                c=table;
                return true;
            }
        }
        return false;
    }

    @Override
    public Table findbyId(Integer ID) {
        for(Table c : tableList){
            if(c.getTableId().equals(ID)){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Table> getAll() {
        return tableList;
    }

    @Override
    public List<Waiter> getWaitersAtTable(Integer ID) {
        for(Table t: tableList)
        {
            if (ID.equals(t.getTableId()))
            {
                return t.getWaiterList();
            }
        }
        return null;

    }

    @Override
    public void setWaiterListAtTable(Waiter waiter, Table table) {
        for(Table t: tableList)
        {
            if(t.equals(table))
            {
                t.getWaiterList().add(waiter);
            }
        }
    }
}
