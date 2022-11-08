package Controller;

import Model.*;
import Repository.inMemory.InMemoryClientRepo;
import Repository.inMemory.InMemoryReservationRepo;
import Repository.inMemory.InMemoryTableRepo;
import Repository.inMemory.InMemoryWaiterRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryController implements IController{

    private InMemoryReservationRepo inMemoryReservationRepo;
    private InMemoryTableRepo inMemoryTableRepo;
    private InMemoryClientRepo inMemoryClientRepo;

    public InMemoryController(InMemoryReservationRepo inMemoryReservationRepo, InMemoryTableRepo inMemoryTableRepo, InMemoryClientRepo inMemoryClientRepo, InMemoryWaiterRepo inMemoryWaiterRepo) {
        this.inMemoryReservationRepo = inMemoryReservationRepo;
        this.inMemoryTableRepo = inMemoryTableRepo;
        this.inMemoryClientRepo = inMemoryClientRepo;
        this.inMemoryWaiterRepo = inMemoryWaiterRepo;
    }

    private InMemoryWaiterRepo inMemoryWaiterRepo;

    public InMemoryReservationRepo getInMemoryReservationRepo() {
        return inMemoryReservationRepo;
    }

    public void setInMemoryReservationRepo(InMemoryReservationRepo inMemoryReservationRepo) {
        this.inMemoryReservationRepo = inMemoryReservationRepo;
    }

    public InMemoryTableRepo getInMemoryTableRepo() {
        return inMemoryTableRepo;
    }

    public void setInMemoryTableRepo(InMemoryTableRepo inMemoryTableRepo) {
        this.inMemoryTableRepo = inMemoryTableRepo;
    }

    public InMemoryClientRepo getInMemoryClientRepo() {
        return inMemoryClientRepo;
    }

    public void setInMemoryClientRepo(InMemoryClientRepo inMemoryClientRepo) {
        this.inMemoryClientRepo = inMemoryClientRepo;
    }

    public InMemoryWaiterRepo getInMemoryWaiterRepo() {
        return inMemoryWaiterRepo;
    }

    public void setInMemoryWaiterRepo(InMemoryWaiterRepo inMemoryWaiterRepo) {
        this.inMemoryWaiterRepo = inMemoryWaiterRepo;
    }

    @Override
    public List<Table> availableTables(Date date) {
        List<Table> avTables = new ArrayList<>();
        for(Reservation reservation: inMemoryReservationRepo.getAll()) {
            if (!reservation.getDate().equals(date))
            {
                avTables.add(reservation.getTable());
            }
        }
        if(!avTables.isEmpty()) return avTables;
        return null;
    }

    @Override
    public Reservation makeNewReservation(Client client, Integer nrPersons, Date date) {

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
        this.inMemoryReservationRepo.add(reservation);
        return reservation;

    }

    @Override
    public List<Table> waitersAtTable(Table table) {
        List<Table> noWaitersTable = new ArrayList<>();
        for (Table t: inMemoryTableRepo.getAll())
        {
            if (t==table) {
                if (table.getWaiterList() == null)
                {
                    noWaitersTable.add(table);
                }
            }
        }

        return noWaitersTable;
    }

    @Override
    public List<Table> tablesForWaiter(Waiter waiter) {
        return null;
    }

    @Override
    public Waiter addWaiteratTable(Table table) {
        return null;
    }

    @Override
    public List<Reservation> reservationAtDate(Date date) {
        return null;
    }

    @Override
    public List<Reservation> allReservations() {
        return null;
    }
}
