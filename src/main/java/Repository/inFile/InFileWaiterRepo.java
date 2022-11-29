package Repository.inFile;

import Model.Table;
import Model.Waiter;
import Repository.IWaiterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InFileWaiterRepo implements IWaiterRepository {

    public List<Waiter> waiterList;
    ObjectMapper objectMapper = new ObjectMapper();
    public InFileWaiterRepo(String filename) {
        this.filename = filename;
        waiterList = new ArrayList<>();
        try {
            waiterList = objectMapper.readValue(new File(filename), new TypeReference<List<Waiter>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String filename;
    @Override
    public boolean add(Waiter waiter) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(Integer integer, Waiter waiter) {
        return false;
    }

    @Override
    public Waiter findbyId(Integer integer) {
        return null;
    }

    @Override
    public List<Waiter> getAll() {
        return null;
    }

    @Override
    public List<Table> getTablesForWaiter(Integer ID) {
        return null;
    }

    @Override
    public void setTableForWaiter(Waiter waiter, Table table) {

    }
}
