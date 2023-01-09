package Tests;

import Controller.Controller;
import Model.Client;
import Model.Reservation;
import Model.Table;
import Model.Waiter;
//import Repository.inFile.InFileClientRepo;
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
    public void setUp()
    {
        inMemoryReservationRepo =new InMemoryReservationRepo();
        inMemoryClientRepo=new InMemoryClientRepo();
        inMemoryTableRepo=new InMemoryTableRepo();
        inMemoryWaiterRepo=new InMemoryWaiterRepo();

        inMemoryClientRepo.populate();
        inMemoryTableRepo.populate();
        inMemoryWaiterRepo.populate();
        Reservation rez = new Reservation(inMemoryClientRepo.getAll().get(0),"27-10-2002", inMemoryTableRepo.getAll().get(0),2);
        inMemoryReservationRepo.add(rez);

        Reservation rez1 = new Reservation(inMemoryClientRepo.getAll().get(0),"0-0-0", inMemoryTableRepo.getAll().get(0),2);
        Reservation rez2 = new Reservation(inMemoryClientRepo.getAll().get(0),"0-0-0", inMemoryTableRepo.getAll().get(1),2);
        Reservation rez3 = new Reservation(inMemoryClientRepo.getAll().get(0),"0-0-0", inMemoryTableRepo.getAll().get(2),2);
        inMemoryReservationRepo.add(rez1);
        inMemoryReservationRepo.add(rez2);
        inMemoryReservationRepo.add(rez3);
        controller=new Controller(inMemoryClientRepo, inMemoryWaiterRepo, inMemoryReservationRepo, inMemoryTableRepo);
    }


    @Test
    public void testMakeNewReservation() {
        Client client = new Client("Ana", "Lazar", 1, "074593456", new ArrayList<>());
        int nrPersons = 5;
        String date = "0-1-0";
        Reservation testRez = controller.makeNewReservation(client, nrPersons, date);
        Integer expectedID = 1;
        assertEquals(testRez.getClient(),client);
        assertEquals(testRez.getDate(),date);


    }

    @Test
    public void testAvailableTables()
    {

        Table table2 = new Table(2, 2, new ArrayList<>());
        Table table3 = new Table(3, 10, new ArrayList<>());



        List<Table> expectedArray = new ArrayList<>();

        expectedArray.add(table2);
        expectedArray.add(table3);
        List<Table> avTables= controller.availableTables("27-10-2002");
        assertEquals(expectedArray, avTables);

        List<Table> avTables2=controller.availableTables("0-0-0");
        assertNull(avTables2);
    }




    @Test
    public void testaddWaiterAtTable()
    {
        Table table = new Table(5, 20, new ArrayList<>());
        Waiter waiter=controller.addWaiterAtTable(table);

        for(Waiter w: inMemoryWaiterRepo.getAll())
        {
            if(w.getWaiterID()==2)
            {
                assertEquals(waiter, w);
                break;
            }
        }


    }
    @Test
    public void testReservationAtDate()
    {
        List<Reservation> reservations1 = controller.reservationAtDate("27-10-2002");
        assertEquals(reservations1.size(),1);
        List<Reservation> reservations2 = controller.reservationAtDate("29-10-2023");
        assertEquals(reservations2.size(),0);
    }

}



