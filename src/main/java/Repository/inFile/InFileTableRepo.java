package Repository.inFile;

import Model.Table;
import Repository.ITableRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InFileTableRepo implements ITableRepository {

    ObjectMapper objectMapper = new ObjectMapper();
    public List<Table> tableList;

    public InFileTableRepo(String filename) {
        tableList = new ArrayList<>();
        this.filename = filename;
        try {
            tableList = objectMapper.readValue(new File(filename), new TypeReference<List<Table>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String filename;
    @Override
    public boolean add(Table table) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(Integer integer, Table table) {
        return false;
    }

    @Override
    public Table findbyId(Integer integer) {
        return null;
    }

    @Override
    public List<Table> getAll() {
        return null;
    }
}
