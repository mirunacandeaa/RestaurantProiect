package Repository.JDBA;

import Model.Client;
import Repository.IClientRepository;

import java.util.List;

public class JDBAClientRepo implements IClientRepository {
    @Override
    public boolean add(Client client) {
        return false;
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
