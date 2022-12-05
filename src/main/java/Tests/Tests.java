package Tests;

import Controller.Controller;
import Model.Reservation;
import Model.Table;
import Repository.inFile.InFileClientRepo;
import Repository.inMemory.InMemoryClientRepo;
import Repository.inMemory.InMemoryReservationRepo;
import Repository.inMemory.InMemoryTableRepo;
import Repository.inMemory.InMemoryWaiterRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

///class with function that test if the functions from controller are working correctly
public class Tests {


    private InMemoryTableRepo inMemoryTableRepo;
    private InMemoryReservationRepo inMemoryReservationRepo;
    private InMemoryWaiterRepo inMemoryWaiterRepo;
    private InMemoryClientRepo inMemoryClientRepo;

    Controller controller;
    @Before
    public void SetUp()
    {
        inMemoryReservationRepo=new InMemoryReservationRepo();
        inMemoryClientRepo=new InMemoryClientRepo();
        inMemoryTableRepo=new InMemoryTableRepo();
        inMemoryWaiterRepo=new InMemoryWaiterRepo();

        inMemoryClientRepo.populate();
        inMemoryTableRepo.populate();
        inMemoryWaiterRepo.populate();
        controller=new Controller(inMemoryClientRepo, inMemoryWaiterRepo, inMemoryReservationRepo, inMemoryTableRepo);
    }

    @Test
    public void testMakeNewReservation()
    {

    }

    @Test
    public void testAvailableTables()
    {

        Table table2 = new Table(2, 2, new ArrayList<>());
        Table table3 = new Table(3, 10, new ArrayList<>());

        Reservation rez = new Reservation(inMemoryClientRepo.getAll().get(0),"27-10-2002", inMemoryTableRepo.getAll().get(0),2);
        inMemoryReservationRepo.add(rez);

        Reservation rez1 = new Reservation(inMemoryClientRepo.getAll().get(0),"0-0-0", inMemoryTableRepo.getAll().get(0),2);
        Reservation rez2 = new Reservation(inMemoryClientRepo.getAll().get(0),"0-0-0", inMemoryTableRepo.getAll().get(1),2);
        Reservation rez3 = new Reservation(inMemoryClientRepo.getAll().get(0),"0-0-0", inMemoryTableRepo.getAll().get(2),2);
        inMemoryReservationRepo.add(rez1);
        inMemoryReservationRepo.add(rez2);
        inMemoryReservationRepo.add(rez3);

        List<Table> expectedArray = new ArrayList<>();

        expectedArray.add(table2);
        expectedArray.add(table3);
        List<Table> avTables= controller.availableTables("27-10-2002");
        assertEquals(expectedArray, avTables);

        List<Table> avTables2=controller.availableTables("0-0-0");


    }


}
