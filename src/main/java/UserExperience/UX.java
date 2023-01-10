package UserExperience;

import Controller.Controller;
import Model.Waiter;
import Repository.IClientRepository;
import Repository.IReservationRepository;
import Repository.ITableRepository;
import Repository.IWaiterRepository;
import Utils.InvalidDataException;
import Utils.InvalidNameException;
import Utils.InvalidPasswordException;
import Utils.InvalidRoleException;
import View.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.System.exit;


///the interface - takes info from the view
public class UX {
    Scanner scan = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner logscan = new Scanner(System.in);


    private View view;

    public UX(View view) {
        this.view = view;
    }



    public int loginCreds() throws IOException, InvalidPasswordException, InvalidNameException {
        //admin name: Popa Antonia
        //password: 1234lol

        int check = 0;

        System.out.println("Please log in:");
        System.out.println("Name:");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String Namen = br.readLine();
        int WrongPass=0;
        while(true) {
            try {
                if (view.checkUser(Namen)) {
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("Password:");
                    String PassWord = br2.readLine();
                    if (view.checkPass(Namen,PassWord))
                        return 1;
                    else {
                        throw new InvalidPasswordException(" ");
                    }
                }
                else{
                    break;
                }
            } catch (InvalidPasswordException e) {
                WrongPass++;
                System.out.println("The password for this user is invalid");
                if (WrongPass > 2) {
                    System.out.println("You wrote the wrong password too many times. Loser");
                    exit(0);
                }
            }
        }



        try
        {
            for (Waiter w : view.getWaiters()) {
                System.out.println(w.getName());
                if (w.getName().equals(Namen)) {
                    check = 1;
                }
            }

            if (check != 1) {
                throw new InvalidNameException("The user does not exist");
            }
        }
        catch (InvalidNameException E)
        {
            System.out.println("The user does not exist");
            loginCreds();
        }


        for (Waiter w : view.getWaiters()) {

            if (w.getName().equals(Namen)) {
                System.out.println("Password:");
                while (true) {
                    try {
                        int PassWord = logscan.nextInt();
                        if (PassWord != w.getWaiterID()) {
                            throw new InvalidPasswordException("The password for this user is invalid");
                        } else {
                            return 0;
                        }
                    } catch (InvalidPasswordException E) {
                        WrongPass++;
                        System.out.println("The password for this user is invalid");
                        if (WrongPass > 2) {
                            System.out.println("You wrote the wrong password too many times. Loser");
                            exit(0);
                        }
                    }
                }

            }
        }

        return 2;
    }

    public void continueprogram(int role) throws InvalidDataException {
        System.out.println("Do you want to continue? \n 1.Quit\n 2.Continue ");

        int decision= scan.nextInt();
        try{
            if(decision==1)
            {
                exit(0);
            }
            if(decision!=2)
            {
                throw new InvalidDataException(" ");
            }
        }
        catch (InvalidDataException E)
        {
            System.out.println("The number you typed is invalid");
            if(role==1)
            {
                adminmenu(role);
            }
            if(role==0)
            {
                waitermenu(role);
            }

        }
    }


    public void adminmenu(int role) throws InvalidDataException {
        while(true)
        {
            System.out.println("What would you like to do? \n 1.Confirm new Reservation  \n 2.See available Tables \n 3.See all Reservations \n 4.View reservations at date \n 5.Add Waiter at Table"  );
            int num= scan.nextInt();
            try{
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
            }
            catch (InvalidDataException E)
            {
                System.out.println("The number you typed is invalid");
                adminmenu(role);
            }


            continueprogram(role);
        }
    }


    public void waitermenu(int role) throws InvalidDataException {
        while(true) {
            System.out.println("What would you like to do?  \n 1.See all Reservations \n 2.View reservations at date \n 3.See available Tables");
            int num= scan.nextInt();
            try{
                if(num==1) {
                    view.viewReservations();
                }
                if(num==2)
                {
                    view.viewReservationsAtDate();
                }
                if(num==3)
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
                if(num<1 || num>3)
                {
                    throw new InvalidDataException("The number you typed is invalid");
                }
            }
            catch (InvalidDataException E)
            {
                System.out.println("The number you typed is invalid");
                waitermenu(role);
            }

            continueprogram(role);

        }

    }



    public void chooseWhatUWantToDo() throws InvalidDataException, IOException, InvalidNameException, InvalidPasswordException, InvalidRoleException {


        System.out.println("Welcome to your restaurant app that manages everything. ");
        int role= loginCreds();
        try {
            if (role == 1) {
                adminmenu(role);
            }
            if (role == 0) {
                waitermenu(role);
            }
            else if(role < 0 || role>1)
            {
                throw new InvalidRoleException("Not a valid role");

            }
        }
        catch (InvalidRoleException E)
        {
            System.out.println("Something went wrong");
            loginCreds();
        }

    }

}
