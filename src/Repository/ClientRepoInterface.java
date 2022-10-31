package Repository;

import Model.Client;

import java.util.List;

public interface ClientRepoInterface extends ICrudRepository<String, Client> {
    List<Client>getAllClients();
}
