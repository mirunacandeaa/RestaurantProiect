package Repository.inFile;

import Model.Waiter;
import Repository.IWaiterRepository;

import java.util.List;

public class WaiterRepo implements IWaiterRepository {
    List<Waiter> waiterList;

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
    public List<Waiter> getAllWaiters() {
        return waiterList;
    }
}
