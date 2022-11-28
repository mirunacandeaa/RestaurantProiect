package Repository.inMemory;

import Model.Table;
import Model.Waiter;
import Repository.IWaiterRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryWaiterRepo implements IWaiterRepository {
    private List<Waiter> waiterList = new ArrayList<>();

    public void populate(){
        Waiter waiter1 = new Waiter("Andrei", "Ivanovici", 1, null);
        Waiter waiter2 = new Waiter("Razvan", "Calasnicov", 2, null);
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
}
