package Controller;

import Model.Client;
import Model.Reservation;
import Model.Table;
import Model.Waiter;
import Repository.inFile.InFileClientRepo;
import Repository.inFile.InFileReservationRepo;
import Repository.inFile.InFileTableRepo;
import Repository.inFile.InFileWaiterRepo;

import java.util.Date;
import java.util.List;

public class InFileController implements IController{

    private InFileClientRepo inFileClientRepo;
    private InFileWaiterRepo inFileWaiterRepo;
    private InFileReservationRepo inFileReservationRepo;
    private InFileTableRepo inFileTableRepo;


    public InFileController(InFileClientRepo inFileClientRepo, InFileWaiterRepo inFileWaiterRepo, InFileReservationRepo inFileReservationRepo, InFileTableRepo inFileTableRepo) {
        this.inFileClientRepo = inFileClientRepo;
        this.inFileWaiterRepo = inFileWaiterRepo;
        this.inFileReservationRepo = inFileReservationRepo;
        this.inFileTableRepo = inFileTableRepo;
    }


    public InFileClientRepo getInFileClientRepo() {
        return inFileClientRepo;
    }

    public void setInFileClientRepo(InFileClientRepo inFileClientRepo) {
        this.inFileClientRepo = inFileClientRepo;
    }

    public InFileWaiterRepo getInFileWaiterRepo() {
        return inFileWaiterRepo;
    }

    public void setInFileWaiterRepo(InFileWaiterRepo inFileWaiterRepo) {
        this.inFileWaiterRepo = inFileWaiterRepo;
    }

    public InFileReservationRepo getInFileReservationRepo() {
        return inFileReservationRepo;
    }

    public void setInFileReservationRepo(InFileReservationRepo inFileReservationRepo) {
        this.inFileReservationRepo = inFileReservationRepo;
    }

    public InFileTableRepo getInFileTableRepo() {
        return inFileTableRepo;
    }

    public void setInFileTableRepo(InFileTableRepo inFileTableRepo) {
        this.inFileTableRepo = inFileTableRepo;
    }


    @Override
    public Reservation makeNewReservation(Client client, Integer nrPersons, Date date) {
        return null;
    }

    @Override
    public List<Table> availableTables(Date date) {
        return null;
    }

    @Override
    public List<Table> waitersAtTable(Table table) {
        return null;
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
