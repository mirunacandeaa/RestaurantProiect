package Model;

import java.util.List;

public class Client extends Person{


    Integer clientID;
    String phone;
    List<Reservation> reservationList;

    public Client(String firstName, String lastName, Integer clientID, String phone, List<Reservation> reservationList) {
        super(firstName, lastName);
        this.clientID = clientID;
        this.phone = phone;
        this.reservationList = reservationList;
    }



    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public String toString(){
        return "ID: " + clientID + " Name: " + firstName + " " + lastName;
    }
}
