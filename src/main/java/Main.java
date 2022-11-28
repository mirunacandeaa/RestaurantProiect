import Controller.Controller;
import Model.Client;
import Model.Reservation;
import Model.Table;
import Model.Waiter;
import Repository.inFile.InFileClientRepo;
import Repository.inMemory.InMemoryClientRepo;
import Repository.inMemory.InMemoryReservationRepo;
import Repository.inMemory.InMemoryTableRepo;
import Repository.inMemory.InMemoryWaiterRepo;
import UserExperience.UX;
import View.View;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        InMemoryTableRepo tableRepo = new InMemoryTableRepo();
        InMemoryWaiterRepo waiterRepo = new InMemoryWaiterRepo();
        InMemoryClientRepo clientRepo = new InMemoryClientRepo();
        InMemoryReservationRepo reservationRepo = new InMemoryReservationRepo();

        tableRepo.populate();
        clientRepo.populate();
        waiterRepo.populate();
        Reservation rez1 = new Reservation(clientRepo.getAll().get(0),"27-10-2002", tableRepo.getAll().get(0),2);
        reservationRepo.add(rez1);

        Controller controller=new Controller(clientRepo, waiterRepo, reservationRepo, tableRepo);
        View view =new View(controller);
        view.printnewReservation();
        UX ux=new UX(view);
        ux.chooseWhatUWantToDo();




    }
}