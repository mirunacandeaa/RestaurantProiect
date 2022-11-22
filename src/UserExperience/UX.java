package UserExperience;

import Controller.Controller;
import Repository.IClientRepository;
import Repository.IReservationRepository;
import Repository.ITableRepository;
import Repository.IWaiterRepository;
import View.View;

import java.util.Scanner;

public class UX {
    Scanner scan= new Scanner(System.in);
    private View view;


    public UX(View view) {
        this.view = view;
    }




    public void chooseWhatUWantToDo()
    {
        System.out.println("What would you like to do? \n 1.Confirm new Reservation  \n 2.See available Tables");
        int num= scan.nextInt();

        /*
        if(num==1)
        {
            view.printnewReservation();
        }*/
        if(num==2)
        {
            view.printavailableTables("21-12-2022");
        }
    }

}
