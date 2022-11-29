package Repository.inMemory;

import Model.Table;
import Model.Waiter;
import Repository.IWaiterRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryWaiterRepo implements IWaiterRepository {
    private List<Waiter> waiterList = new ArrayList<>();

    public void populate(){
        Waiter waiter1 = new Waiter("Andrei", "Ivanovici", 1, new ArrayList<>());
        Waiter waiter2 = new Waiter("Razvan", "Calasnicov", 2, new ArrayList<>());
        waiterList.add(waiter1);
        waiterList.add(waiter2);
    }

    @Override
    public boolean add(Waiter waiter) {
        for(Waiter c : waiterList)
            if(c.equals(waiter))
                return false;
        waiterList.add(waiter);
        return true;
    }

    @Override
    public boolean delete(Integer ID) {
        for(Waiter c : waiterList)
            if(c.getWaiterID().equals(ID)){
                waiterList.remove(c);
                return true;
            }
        return false;
    }

    @Override
    public boolean update(Integer ID, Waiter waiter) {
        for(Waiter c : waiterList){
            if(c.getWaiterID().equals(ID)){
                c=waiter;
                return true;
            }
        }
        return false;
    }

    @Override
    public Waiter findbyId(Integer ID) {
        for(Waiter c : waiterList){
            if(c.getWaiterID().equals(ID)){
                return c;
            }
        }
        return null;
    }


    @Override
    public List<Waiter> getAll() {
        return waiterList;
    }

    @Override
    public List<Table> getTablesForWaiter(Integer ID) {
        for(Waiter w: waiterList)
        {
            if(w.getWaiterID().equals(ID))
            {
                return w.getTableList();
            }
        }
        return null;
    }

    @Override
    public void setTableForWaiter(Waiter waiter, Table table) {
        for(Waiter w: waiterList)
        {
            if(w.equals(waiter))
            {
                w.getTableList().add(table);
            }
        }
    }
}
