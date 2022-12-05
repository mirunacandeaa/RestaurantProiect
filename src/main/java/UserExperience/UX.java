package UserExperience;

import Controller.Controller;
import Repository.IClientRepository;
import Repository.IReservationRepository;
import Repository.ITableRepository;
import Repository.IWaiterRepository;
import Utils.InvalidDataException;
import View.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.System.exit;


///the interface - takes info from the view
public class UX {
    Scanner scan= new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private View view;


    public UX(View view) {
        this.view = view;
    }




    public void chooseWhatUWantToDo() throws InvalidDataException {
        System.out.println("Welcome to your restaurant app that manages everything. ");
        while(true)
        {
            System.out.println("What would you like to do? \n 1.Confirm new Reservation  \n 2.See available Tables \n 3.See all Reservations \n 4.View reservations at date \n 5.Add Waiter at Table"  );
            int num= scan.nextInt();
            if(num==1)
            {
                view.printnewReservation();
            }
            if(num==2)
            {
                System.out.println("Select the date when you want to see available Tables");
                String Tabledate= null;
                try {
                    Tabledate = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                view.printavailableTables(Tabledate);
            }
            if(num==3)
            {

                view.viewReservations();
            }
            if(num==4){
                view.viewReservationsAtDate();
            }

            if(num==5)
            {
                view.printNewWaiterAtTableAdded();
            }

            if(num<1 || num>5)
            {
                throw new InvalidDataException("The number you typed is invalid");
            }

            System.out.println("Do you want to continue? \n 1.Quit\n 2.Continue ");
            int decision= scan.nextInt();
            if(decision==1)
            {
                exit(0);
            }
            if(decision!=2)
            {
                System.out.println("You have to type something else, preferably 1 or 2");
                decision= scan.nextInt();
            }



        }

    }

}
