package Repository.inFile;

import Model.Reservation;
import Repository.IReservationRepository;

import java.util.List;

public class InFileReservationRepo implements IReservationRepository {
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
