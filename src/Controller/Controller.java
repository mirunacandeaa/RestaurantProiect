package Controller;

import Model.Client;
import Model.Reservation;
import Model.Table;
import Model.Waiter;
import Repository.IClientRepository;
import Repository.IReservationRepository;
import Repository.ITableRepository;
import Repository.IWaiterRepository;
import View.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

    private IClientRepository clientRepository;
    private IWaiterRepository waiterRepository;
    private IReservationRepository reservationRepository;
    private ITableRepository tableRepository;


    public IClientRepository getClientRepository() {
        return clientRepository;
    }

    public void setClientRepository(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public IWaiterRepository getWaiterRepository() {
        return waiterRepository;
    }

    public void setWaiterRepository(IWaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    public IReservationRepository getReservationRepository() {
        return reservationRepository;
    }

    public void setReservationRepository(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ITableRepository getTableRepository() {
        return tableRepository;
    }

    public void setTableRepository(ITableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }


    public List<Table> getAllTables()
    {
        return tableRepository.getAll();
    }



    public Controller(IClientRepository clientRepository, IWaiterRepository waiterRepository, IReservationRepository reservationRepository, ITableRepository tableRepository)
    {
        this.clientRepository=clientRepository;
        this.reservationRepository=reservationRepository;
        this.waiterRepository=waiterRepository;
        this.tableRepository=tableRepository;
    }

    public Reservation makeNewReservation(Client client, Integer nrPersons, String date)
    {
        Integer ID=4;
        //TODO VEZI DE ID SI SCRIE WARNINGU SI TEXTU GEN 'SUCCESFULLY RESERV.'

        List<Table> freeTables = this.availableTables(date);
        Table tableForRes = null;
        for(Table t : freeTables){
            if(t.getNrPersons().equals(nrPersons)){
                tableForRes = t;
                break;
            }
            else if(t.getNrPersons()>nrPersons){
                tableForRes = t;
                break;
            }
        }

        if(tableForRes==null)
        {

            return null;
    }

        Reservation reservation=new Reservation(ID, client, date, tableForRes, nrPersons);
        this.reservationRepository.add(reservation);
        return reservation;

    }

    public List<Table> availableTables(String date)
    {
        List<Table> avTables = new ArrayList<>();
        for(Reservation reservation: reservationRepository.getAll()) {
            if (!reservation.getDate().equals(date))
            {
                avTables.add(reservation.getTable());
            }
        }
        if(!avTables.isEmpty())
        {
            return avTables;
        }
        return null;
    }


    public List<Table> waitersAtTable(Table table)
    {
        return null;
    }

    public List<Table> tablesForWaiter(Waiter waiter)
    {
        return null;
    }
    public Waiter addWaiteratTable(Table table)
    {
        return null;
    }
    public List<Reservation> reservationAtDate(Date date)
    {
        return null;
    }
    public List<Reservation> allReservations()
    {
        return null;
    }
}
