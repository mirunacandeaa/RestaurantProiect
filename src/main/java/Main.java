import Controller.Controller;
import Model.Client;
import Model.Reservation;
import Model.Table;
import Model.Waiter;

import Repository.JDBA.JDBAClientRepo;
import Repository.JDBA.JDBAReservationRepo;
import Repository.JDBA.JDBATableRepo;
import Repository.JDBA.JDBAWaiterRepo;
import Repository.inMemory.InMemoryClientRepo;
import Repository.inMemory.InMemoryReservationRepo;
import Repository.inMemory.InMemoryTableRepo;
import Repository.inMemory.InMemoryWaiterRepo;
import UserExperience.UX;
import Utils.InvalidDataException;

import Utils.InvalidNameException;
import Utils.InvalidPasswordException;
import Utils.InvalidRoleException;
import View.View;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//initialising the program and apel the UX
public class Main {
    public static void main(String[] args) throws InvalidDataException, InvalidNameException, InvalidPasswordException, InvalidRoleException, IOException {

        InMemoryTableRepo tableRepo = new InMemoryTableRepo();
        InMemoryWaiterRepo waiterRepo = new InMemoryWaiterRepo();
        InMemoryClientRepo clientRepo = new InMemoryClientRepo();
        InMemoryReservationRepo reservationRepo = new InMemoryReservationRepo();

        tableRepo.populate();
        clientRepo.populate();
        waiterRepo.populate();
        Reservation rez1 = new Reservation(clientRepo.getAll().get(0),"27-10-2003", tableRepo.getAll().get(0),2);
        Reservation rez2 = new Reservation(clientRepo.getAll().get(0),"27-10-2004", tableRepo.getAll().get(1),4);
        Reservation rez3 = new Reservation(clientRepo.getAll().get(0),"27-09-2002", tableRepo.getAll().get(2),10);


        reservationRepo.add(rez1);
        reservationRepo.add(rez2);
        reservationRepo.add(rez3);

        List<Waiter> waiterList = new ArrayList<>();
        waiterList.add(waiterRepo.getAll().get(0));
        tableRepo.getAll().get(0).setWaiterList(waiterList);
        System.out.println(tableRepo.getWaitersAtTable(1));

        Controller controller=new Controller(clientRepo, waiterRepo, reservationRepo, tableRepo);
        View view =new View(controller);
        UX ux=new UX(view);
        ux.chooseWhatUWantToDo();

/*        JDBATableRepo jdbaTableRepo = new JDBATableRepo();
        JDBAWaiterRepo jdbaWaiterRepo = new JDBAWaiterRepo();
        JDBAClientRepo jdbaClientRepo = new JDBAClientRepo();
        JDBAReservationRepo jdbaReservationRepo = new JDBAReservationRepo();
        jdbaTableRepo.actualiseWaitersAtTables();
        jdbaWaiterRepo.actualiseWaitersAtTables();
        jdbaClientRepo.addReservations();
        Controller controller = new Controller(jdbaClientRepo,jdbaWaiterRepo,jdbaReservationRepo,jdbaTableRepo);
        View view = new View(controller);
        UX ux = new UX(view);
        ux.chooseWhatUWantToDo();*/
    }
}
