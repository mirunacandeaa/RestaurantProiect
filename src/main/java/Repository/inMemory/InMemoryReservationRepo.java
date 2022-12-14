package Repository.inMemory;

import Model.Reservation;
import Repository.IReservationRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryReservationRepo implements IReservationRepository {

    public List<Reservation> reservationList = new ArrayList<>();


    @Override
    public boolean add(Reservation reservation) {
        for(Reservation c : reservationList)
            if(c.equals(reservation))
                return false;
        reservationList.add(reservation);
        return true;
    }

    @Override
    public boolean delete(Integer ID) {
        for(Reservation c : reservationList)
            if(c.getReservationID().equals(ID)){
                reservationList.remove(c);
                return true;
            }
        return false;
    }


    @Override
    public boolean update(Integer ID, Reservation reservation) {
        for(Reservation c : reservationList){
            if(c.getReservationID().equals(ID)){
                reservation.setReservationID(ID);
                c=reservation;
                return true;
            }
        }
        return false;
    }

    @Override
    public Reservation findbyId(Integer ID) {
        for(Reservation c : reservationList){
            if(c.getReservationID().equals(ID)){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        return reservationList;
    }
}
