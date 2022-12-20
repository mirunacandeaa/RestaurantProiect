package View;
import Controller.Controller;
import Model.Client;
import Model.Reservation;
import Model.Table;
import Model.Waiter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

///the view printing the dates you take from the controller
public class View {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    Scanner scan= new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    ///print the available tables on a certain date
    public void printavailableTables(String date){
        System.out.println("Available Tables");

        if(this.controller.availableTables(date)==null)
        {
            System.out.println("No available tables");
            return;
        }
        for(Table t : this.controller.availableTables(date)){
            System.out.println("Table id: " + t.getTableId());
        }
    }


    ///print a new Reservation that contains a client, a table and a nr of persons
    public void printnewReservation(){
        System.out.println("Select Client: ");
        for (Client c : controller.getClients()){
            System.out.println(c.toString());
        }
        int cID = scan.nextInt();
        cID = cID-1;
        Client selected = controller.selectClient(cID);
        System.out.println("How many persons for reservations: ");
        int nr = scan.nextInt();
        System.out.println("Enter date(format must be yyyy-mm-dd): ");
        String date = null;
        try {
            date = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Reservation newRez = controller.makeNewReservation(selected,nr,date);
        if(newRez==null){
            System.out.println("No available tables for that many persons at that date. Try again.");
            printnewReservation();
        }
        System.out.println("Reservation created ");
        System.out.println("Reservation ID: " + newRez.getReservationID());
        System.out.println("Client: " + newRez.getClient());
        System.out.println("Reservation date: " + newRez.getDate());
        System.out.println("Table number" + newRez.getTable());
    }

    ///print all reservations
    public void viewReservations(){
        List<Reservation> allRez = controller.allReservations();
        if(allRez.isEmpty())
            System.out.println("No reservations");
        else
            for(Reservation r : allRez){
                System.out.println(r.toString());
            }
    }

    ///print all the reservation at a certain date
    public void viewReservationsAtDate(){
        System.out.println("Enter date: ");
        try {
            String date = br.readLine();
            List<Reservation> res = controller.reservationAtDate(date);
            if(res.isEmpty())
                System.out.println("No reservations at " + date);
            else
                for(Reservation r : res){
                    System.out.println(r.toString());
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ///see the new waiter added at a certain table
    public void printNewWaiterAtTableAdded() {
        System.out.println("Choose TableID:");
        int nr= scan.nextInt();
        nr=nr-1;
        Table t=controller.selectTable(nr);
        if (controller.addWaiterAtTable(t) == null)
        {
            System.out.println("No waiter added");
        }
        else {
            System.out.println("Waiter added:");
            System.out.println(controller.addWaiterAtTable(t).toString());
        }
    }

}

