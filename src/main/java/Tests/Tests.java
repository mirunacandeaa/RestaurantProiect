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

///

/**
 * class with functions that test if the functions from controller are working correctly
 */
public class Tests {


    private InMemoryTableRepo inMemoryTableRepo;
    private InMemoryReservationRepo inMemoryReservationRepo;
    private InMemoryWaiterRepo inMemoryWaiterRepo;
    private InMemoryClientRepo inMemoryClientRepo;

    Controller controller;

    /**
     * data initialising
     */
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

    /**
     * test for the method make new reservation
     */
//    @Test
//    public void testMakeNewReservation() {
//        Client client = new Client("Ana", "Lazar", 1, "074593456", new ArrayList<>());
//        int nrPersons = 5;
//        String date = "0-1-0";
//        Reservation testRez = controller.makeNewReservation(client, nrPersons, date);
//        Integer expectedID = 1;
//        assertEquals(testRez.getClient(),client);
//        assertEquals(testRez.getDate(),date);
//
//
//    }
//
//    /**
//     * test for the method available tables
//     */
//    @Test
//    public void testAvailableTables()
//    {
//
//        Table table2 = new Table(2, 2, new ArrayList<>());
//        Table table3 = new Table(3, 10, new ArrayList<>());
//
//
//
//        List<Table> expectedArray = new ArrayList<>();
//
//        expectedArray.add(table2);
//        expectedArray.add(table3);
//        List<Table> avTables= controller.availableTables("27-10-2002");
//        assertEquals(expectedArray, avTables);
//
//        List<Table> avTables2=controller.availableTables("0-0-0");
//        assertNull(avTables2);
//    }
//
//
//    /**
//     * test for the method add waiters at table
//     */
//    @Test
//    public void testaddWaiterAtTable()
//    {
//        Table table = new Table(5, 20, new ArrayList<>());
//        Waiter waiter=controller.addWaiterAtTable(table);
//
//        for(Waiter w: inMemoryWaiterRepo.getAll())
//        {
//            if(w.getWaiterID()==2)
//            {
//                assertEquals(waiter, w);
//                break;
//            }
//        }
//
//
//    }
//
//    /**
//     * test for the method reservation at date
//     */
//    @Test
//    public void testReservationAtDate()
//    {
//        List<Reservation> reservations1 = controller.reservationAtDate("27-10-2002");
//        assertEquals(reservations1.size(),1);
//        List<Reservation> reservations2 = controller.reservationAtDate("29-10-2023");
//        assertEquals(reservations2.size(),0);
//    }

    @Test
    public void testRightNrPersons()
    {
        List<Integer> ec1 = new ArrayList<>();
        ec1.add(1);
        ec1.add(2);
        ec1.add(3);
        ec1.add(4);
        ec1.add(5);
        ec1.add(6);

        List<Integer> ec2 = new ArrayList<>();
        for (int i = -100; i <= 0; i++) {
            ec2.add(i);
        }

        List<Integer> ec3 = new ArrayList<>();
        for (int i = 7; i <= 100; i++) {
            ec3.add(i);
        }

        int T01=2;

        int valid=0;
        for (int i : ec1) {
            if(i==T01)
            {
                valid=1;
            }
        }

        int invalid=0;
        for(int i: ec2)
        {
            if (i==T01)
            {
                invalid=1;
            }
        }

        for(int i: ec3)
        {
            if (i==T01)
            {
                invalid=1;
            }
        }

        System.out.println("T01 valid: " + valid);
        System.out.println("T01 invalid: " + invalid);


        int T02=8;

        int valid2=0;
        for (int i : ec1) {
            if(i==T02)
            {
                valid2=1;
            }
        }

        int invalid2=0;
        for(int i: ec2)
        {
            if (i==T02)
            {
                invalid2=1;
            }
        }

        for(int i: ec3)
        {
            if (i==T02)
            {
                invalid2=1;
            }
        }

        System.out.println("T02 valid: " + valid2);
        System.out.println("T02 invalid: " + invalid2);

        int T03=-10;

        int valid3=0;
        for (int i : ec1) {
            if(i==T03)
            {
                valid3=1;
            }
        }

        int invalid3=0;
        for(int i: ec2)
        {
            if (i==T03)
            {
                invalid3=1;
            }
        }

        for(int i: ec3)
        {
            if (i==T03)
            {
                invalid3=1;
            }
        }

        System.out.println("T03 valid: " + valid3);
        System.out.println("T03 invalid: " + invalid3);


        int BVAUnter1=0;

        boolean validBVA1=false;
        for (int i : ec1) {
            if(i==BVAUnter1)
            {
                validBVA1=true;
            }
        }

        System.out.println("0 valid: " + validBVA1);


        int BVAUnter2=1;

        boolean validBVA2=false;
        for (int i : ec1) {
            if(i==BVAUnter2)
            {
                validBVA2=true;
            }
        }

        System.out.println("1(Grenzwert) valid: " + validBVA2);


        int BVAUnter3=2;

        boolean validBVA3=false;
        for (int i : ec1) {
            if(i==BVAUnter3)
            {
                validBVA3=true;
            }
        }

        System.out.println("2 valid: " + validBVA3);

        int BVAOben1=6;

        boolean validBVA4=false;
        for (int i : ec1) {
            if(i==BVAOben1)
            {
                validBVA4=true;
            }
        }

        System.out.println("6(Grenzwert) valid: " + validBVA4);


        int BVAOben2=7;

        boolean validBVA5=false;
        for (int i : ec1) {
            if(i==BVAOben2)
            {
                validBVA5=true;
            }
        }

        System.out.println("7 valid: " + validBVA5);

        int BVAOben3=8;

        boolean validBVA6=false;
        for (int i : ec1) {
            if(i==BVAOben3)
            {
                validBVA6=true;
            }
        }

        System.out.println("8 valid: " + validBVA6);

    }





}



