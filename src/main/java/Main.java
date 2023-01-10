import Application.Application;
import Controller.Controller;
import Model.Client;
import Model.Reservation;
import Model.Table;
import Model.Waiter;

import Repository.JDBA.*;
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



/**
 * calls the method that starts the app
 */
public class Main {
    public static void main(String[] args) throws InvalidNameException, InvalidDataException, InvalidPasswordException, InvalidRoleException, IOException {
        Application app = new Application();
        app.runInMemory();


    }
}
