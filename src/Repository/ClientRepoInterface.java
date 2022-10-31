package Repository;

import Model.Client;

import java.util.List;

public interface ClientRepoInterface extends ICrudRepository<Integer, Client> {
    List<Client>getAllClients();
}
