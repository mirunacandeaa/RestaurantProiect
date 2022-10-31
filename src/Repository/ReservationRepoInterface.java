package Repository;

import Model.Client;
import Model.Reservation;

import java.util.List;

public interface ReservationRepoInterface extends ICrudRepository<String, Reservation>{
    List<Reservation> getAllReservations();
}
