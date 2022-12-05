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

///the part of the project where the most logic is
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

    public List<Client> getClients() {
        return clientRepository.getAll();
    }

    public Client selectClient(int x){
        List<Client> clientList = clientRepository.getAll();
        return clientList.get(x);
    }

    public Table selectTable(int x)
    {
        List<Table> tableList=tableRepository.getAll();
        return tableList.get(x);
    }

    ///creates a new reservation by giving it a client and a date and checking for the best fit from the available Tables at that date
    public Reservation makeNewReservation(Client client, Integer nrPersons, String date)
    {
        List<Table> freeTables = this.availableTables(date);
        if(freeTables==null){
            return null;
        }
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

        Reservation reservation=new Reservation(client, date, tableForRes, nrPersons);
        this.reservationRepository.add(reservation);
        return reservation;

    }

    ///returns all the available Tables on a certain date
    public List<Table> availableTables(String date)
    {
        if(reservationRepository.getAll().isEmpty())
            return tableRepository.getAll();

        int ok=1;
        List<Table> notavTables = new ArrayList<>();
        for(Reservation reservation: reservationRepository.getAll()) {
            if (reservation.getDate().equals(date))
            {
                notavTables.add(reservation.getTable());
            }
        }

        List<Table> avTables=new ArrayList<>();
        for(Table t: tableRepository.getAll())
        {
            ok=1;
            for(Table a: notavTables)
            {
                if (t.equals(a)) {
                    ok = 0;
                    break;
                }
            }
            if(ok==1) {
                avTables.add(t);
            }
        }

        if(!avTables.isEmpty())
        {
            return avTables;
        }


      //  throw new IllegalArgumentException("No tables are available on this date");
        return null;
    }

    ///returns the number of tables for which a waiter is responsable
    public Integer CountTablesForWitchWaiterResponsable(Waiter waiter)
    {
        return waiter.getTableList().size();
    }

    ///checks which waiter the least number of tables has and assigns him the new table
    public Waiter addWaiterAtTable(Table table)
    {
        int min=0;
        for(Waiter w: waiterRepository.getAll())
        {
            if(w.getTableList().isEmpty())
            {
                tableRepository.setWaiterListAtTable(w, table);
                waiterRepository.setTableForWaiter(w, table);
                return w;
            }
            else if(CountTablesForWitchWaiterResponsable(w)>min)
            {
                min=CountTablesForWitchWaiterResponsable(w);
            }
        }

        for(Waiter w: waiterRepository.getAll())
        {
            if(CountTablesForWitchWaiterResponsable(w).equals(min))
            {

                tableRepository.setWaiterListAtTable(w, table);
                waiterRepository.setTableForWaiter(w, table);
                return w;
            }
        }

        return null;
    }

    ///checks all the reservation at a certain date
    public List<Reservation> reservationAtDate(String date)
    {
        List<Reservation> res = new ArrayList<>();
        if(reservationRepository.getAll().isEmpty()){
            return null;
        }
        for(Reservation r : reservationRepository.getAll()){
            if(r.getDate().equals(date)){
                res.add(r);
            }
        }
        return res;
    }

    ///checks all reservations
    public List<Reservation> allReservations()
    {
        return reservationRepository.getAll();
    }


    ///checks the waiters assigned at a specific table
    public List<Waiter> waitersAtTable(Table table)
    {
        return tableRepository.getWaitersAtTable(table.getTableId());
    }

    ///checks the tables assigned for a specific waiter
    public List<Table> tablesForWaiter(Waiter waiter)
    {
        return waiterRepository.getTablesForWaiter(waiter.getWaiterID());
    }
}