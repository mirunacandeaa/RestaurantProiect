package Repository.inFile;

import Model.Client;
import Repository.IClientRepository;

import java.util.List;

public class ClientRepo implements IClientRepository {
    private List<Client> clientList;

    @Override
    public List<Client> getAllClients() {
        return clientList;
    }

    @Override
    public boolean add(Client client) {
        for(Client c : clientList)
            if(c.equals(client))
                return false;
        clientList.add(client);
        return true;
    }

    @Override
    public boolean delete(Integer ID) {
        for(Client c : clientList)
            if(c.getClientID().equals(ID)){
                clientList.remove(c);
                return true;
        }
        return false;
    }

    @Override
    public boolean update(Integer ID, Client client) {
        for(Client c : clientList){
            if(c.getClientID().equals(ID)){
                c=client;
                return true;
                 }
            }
        return false;
        }

    @Override
    public Client findbyId(Integer ID) {
        for(Client c : clientList){
            if(c.getClientID().equals(ID)){
                return c;
    }
        }
        return null;
    }
}

