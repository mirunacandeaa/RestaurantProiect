package Repository.inMemory;

import Model.Client;
import Repository.IClientRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryClientRepo implements IClientRepository {
    private List<Client> clientList= new ArrayList<>();

    public void populate(){
        Client client1 = new Client("Ana", "Blandiana", 1, "072578954", null);
        Client client2 = new Client("Bella", "Hadid", 2, "074558654", null);
        Client client3= new Client("Selena", "Gomez", 3, "074358654", null);
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
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

    @Override
    public List<Client> getAll() {
        return clientList;
    }
}

