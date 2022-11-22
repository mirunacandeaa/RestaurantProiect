package View;
import Controller.Controller;
import Model.Client;
import Model.Reservation;
import Model.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    Scanner scan= new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void printavailableTables(String date){
        System.out.println("Available Tables");
        if(this.controller.availableTables(date)==null)
        {
            System.out.println("No available tables");
        }
        for(Table t : this.controller.availableTables(date)){
            System.out.println("Table id: " + t.getTableId());
        }
    }


    public void printnewReservation(){
        System.out.println("Select Client: ");
        for (Client c : controller.getClients()){
            System.out.println(c.toString());
        }
        int cID = scan.nextInt();
        Client selected = controller.selectClient(cID);
        System.out.println("How many persons for reservations: ");
        int nr = scan.nextInt();
        System.out.println("Enter date: ");
        String date = null;
        try {
            date = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Reservation newRez = controller.makeNewReservation(selected,nr,date);
        System.out.println("Reservation created ");
        System.out.println("Reservation ID: " + newRez.getReservationID());
        System.out.println("Client: " + newRez.getClient());
        System.out.println("Reservation date: " + newRez.getDate());
        System.out.println("Table number" + newRez.getTable());
    }



}

