package Repository;

import Model.Client;

import java.util.List;

public interface IClientRepository extends ICrudRepository<Integer, Client> {
    List<Client>getAllClients();
}
