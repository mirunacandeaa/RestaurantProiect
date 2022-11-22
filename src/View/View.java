package View;
import Controller.Controller;
import Model.Table;

import java.util.Date;
import java.util.List;

public class View {

    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }



    public void printavailableTables(){
        System.out.println("Available Tables");
        for(Table t : this.controller.availableTables("21-10-2022")){
            System.out.println("Table id: " + t.getTableId());
        }
    }



    public void printnewReservation(){
        if(this.controller.makeNewReservation(, 4, 3)==null)
        {
            System.out.println("Reservation failed, try again with different data.");
        }

        System.out.println("Reservation created: ");
        System.out.println("Reservation ID: " + );
        System.out.println("Client: " + );
        System.out.println("Reservation date: " + );
        System.out.println("Table number" + );
    }



}

