package Controller;

import Model.Client;
import Model.Reservation;
import Model.Table;
import Model.Waiter;
import Repository.IClientRepository;

import java.util.Date;
import java.util.List;

public interface IController {
    public Reservation makeNewReservation(Client client, Integer nrPersons, Date date);
    public List<Table> availableTables(Date date);
    public List<Table> waitersAtTable(Table table);
    public List<Table> tablesForWaiter(Waiter waiter);
    public Waiter addWaiteratTable(Table table);
    public List<Reservation> reservationAtDate(Date date);
    public List<Reservation> allReservations();


}
