package UserExperience;

import Controller.Controller;
import Repository.IClientRepository;
import Repository.IReservationRepository;
import Repository.ITableRepository;
import Repository.IWaiterRepository;
import View.View;

import java.util.Scanner;

import static java.lang.System.exit;


public class UX {
    Scanner scan= new Scanner(System.in);
    private View view;


    public UX(View view) {
        this.view = view;
    }



    public void chooseWhatUWantToDo()
    {
        System.out.println("Welcome to your restaurant app that manages everything. ");
        while(true)
        {
            System.out.println("What would you like to do? \n 1.Confirm new Reservation  \n 2.See available Tables");
            int num= scan.nextInt();
            if(num==1)
            {
                view.printnewReservation();
            }
            if(num==2)
            {
                view.printavailableTables("21-12-2022");
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
