package Application;

import Controller.Controller;
import Model.Reservation;
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
import java.util.ArrayList;
import java.util.List;

/**
 * the Application class containts the methods that starts and populates the app
 */
public class Application {
    /**
     *
     * The runWithDatabase funtion starts the application with the JDBA Repository and populates the database
     *
     * @throws InvalidNameException - if the username is invalid
     * @throws InvalidDataException -if a datatype that the user wants to type is invalid
     * @throws InvalidPasswordException -if the password is invalid
     * @throws InvalidRoleException -if something went wrong while deciding if you login as admin or as waiter
     * @throws IOException -computer exception
     *
     *
     *
     */
    public void runWithDatabase() throws InvalidNameException, InvalidDataException, InvalidPasswordException, InvalidRoleException, IOException {
        JDBATableRepo jdbaTableRepo = new JDBATableRepo();
        JDBAWaiterRepo jdbaWaiterRepo = new JDBAWaiterRepo();
        JDBAClientRepo jdbaClientRepo = new JDBAClientRepo();
        JDBAReservationRepo jdbaReservationRepo = new JDBAReservationRepo();
        jdbaTableRepo.actualiseWaitersAtTables();
        jdbaWaiterRepo.actualiseWaitersAtTables();
        jdbaClientRepo.addReservations();
        Controller controller = new Controller(jdbaClientRepo,jdbaWaiterRepo,jdbaReservationRepo,jdbaTableRepo);
        View view = new View(controller);
        UX ux = new UX(view);
        ux.chooseWhatUWantToDo();
    }

    /**
     *
     * The runInMemory funtion starts the application with the InMemory Repository and populates them
     *
     * @throws InvalidNameException - if the username is invalid
     * @throws InvalidDataException -if a datatype that the user wants to type is invalid
     * @throws InvalidPasswordException -if the password is invalid
     * @throws InvalidRoleException -if something went wrong while deciding if you login as admin or as waiter
     * @throws IOException -computer exception
     *
     *
     *
     */
    public void runInMemory() throws InvalidNameException, InvalidDataException, InvalidPasswordException, InvalidRoleException, IOException {
        InMemoryTableRepo tableRepo = new InMemoryTableRepo();
        InMemoryWaiterRepo waiterRepo = new InMemoryWaiterRepo();
        InMemoryClientRepo clientRepo = new InMemoryClientRepo();
        InMemoryReservationRepo reservationRepo = new InMemoryReservationRepo();

        tableRepo.populate();
        clientRepo.populate();
        waiterRepo.populate();
        Reservation rez1 = new Reservation(clientRepo.getAll().get(0),"2023-10-28", tableRepo.getAll().get(0),2);
        Reservation rez2 = new Reservation(clientRepo.getAll().get(0),"2023-10-28", tableRepo.getAll().get(1),4);
        Reservation rez3 = new Reservation(clientRepo.getAll().get(0),"2002-09-27", tableRepo.getAll().get(2),10);

        reservationRepo.add(rez1);
        reservationRepo.add(rez2);
        reservationRepo.add(rez3);

        List<Waiter> waiterList = new ArrayList<>();
        waiterList.add(waiterRepo.getAll().get(0));
        tableRepo.getAll().get(0).setWaiterList(waiterList);
        waiterRepo.getAll().get(0).getTableList().add(tableRepo.getAll().get(0));

        Controller controller=new Controller(clientRepo, waiterRepo, reservationRepo, tableRepo);
        View view =new View(controller);
        UX ux=new UX(view);
        ux.chooseWhatUWantToDo();
    }

}
