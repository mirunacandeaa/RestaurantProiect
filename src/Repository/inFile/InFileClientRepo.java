package Repository.inFile;

import Model.Client;
import Repository.IClientRepository;

import java.util.List;

public class InFileClientRepo implements IClientRepository {


    public List<Client>clientList;

    @Override
    public boolean add(Client client) {
        for(Client c: clientList)
        {
            if(c.equals(client))
            {
                return false;
            }
        }
        clientList.add(client);
        return true;
    }

    @Override
    public boolean delete(Integer integer) {

        return false;
    }

    @Override
    public boolean update(Integer integer, Client client) {
        return false;
    }

    @Override
    public Client findbyId(Integer integer) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }
}
