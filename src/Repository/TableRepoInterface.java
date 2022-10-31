package Repository;

import Model.Client;
import Model.Table;

import java.util.List;

public interface TableRepoInterface extends ICrudRepository{
    List<Table>getAllTables();
}
