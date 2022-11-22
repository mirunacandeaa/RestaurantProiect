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

    private IClientRepository clientRepository;
    private IWaiterRepository waiterRepository;
    private IReservationRepository reservationRepository;
    private ITableRepository tableRepository;
    private Controller controller;
    private View view;


    public void UXPart()
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
            view.printavailableTables();
        }
    }

}
