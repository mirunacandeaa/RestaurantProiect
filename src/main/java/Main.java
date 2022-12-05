import Controller.Controller;
import Model.Client;
import Model.Reservation;
import Model.Table;
import Model.Waiter;

import Repository.inMemory.InMemoryClientRepo;
import Repository.inMemory.InMemoryReservationRepo;
import Repository.inMemory.InMemoryTableRepo;
import Repository.inMemory.InMemoryWaiterRepo;
import UserExperience.UX;
import Utils.InvalidDataException;
import View.View;

import java.util.ArrayList;
import java.util.List;


//initialising the program and apel the UX
public class Main {
    public static void main(String[] args) throws InvalidDataException {

        InMemoryTableRepo tableRepo = new InMemoryTableRepo();
        InMemoryWaiterRepo waiterRepo = new InMemoryWaiterRepo();
        InMemoryClientRepo clientRepo = new InMemoryClientRepo();
        InMemoryReservationRepo reservationRepo = new InMemoryReservationRepo();

        tableRepo.populate();
        clientRepo.populate();
        waiterRepo.populate();
        Reservation rez1 = new Reservation(clientRepo.getAll().get(0),"27-10-2002", tableRepo.getAll().get(0),2);
        Reservation rez2 = new Reservation(clientRepo.getAll().get(0),"27-10-2002", tableRepo.getAll().get(1),4);
        Reservation rez3 = new Reservation(clientRepo.getAll().get(0),"27-10-2002", tableRepo.getAll().get(2),10);


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




    }
}