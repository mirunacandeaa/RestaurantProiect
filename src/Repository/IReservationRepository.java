package Repository;

import Model.Reservation;

import java.util.List;

public interface IReservationRepository extends ICrudRepository<Integer, Reservation>{
    List<Reservation> getAllReservations();
}
