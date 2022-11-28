package Repository.inFile;

import Model.Reservation;
import Repository.IReservationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InFileReservationRepo implements IReservationRepository {

    ObjectMapper objectMapper = new ObjectMapper();
    public InFileReservationRepo(String filename) {
        this.filename = filename;
        reservationList = new ArrayList<>();
        try {
            reservationList = objectMapper.readValue(new File(filename), new TypeReference<List<Reservation>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Reservation> reservationList;
    private String filename;
    @Override
    public boolean add(Reservation reservation) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(Integer integer, Reservation reservation) {
        return false;
    }

    @Override
    public Reservation findbyId(Integer integer) {
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }
}
