package Repository;

import Model.Client;
import Model.Reservation;
import Model.Table;

import java.util.List;

public class ReservationRepo implements ReservationRepoInterface{

    public List<Reservation> reservationList;


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
    public List<Reservation> getAllReservations() {
        return reservationList;
    }
}
