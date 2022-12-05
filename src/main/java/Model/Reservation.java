package Model;

import java.util.Date;

///class reservation
public class Reservation {

    private static int counter = 0;
    private Integer reservationID;
    private Client client;
    private String date;
    private Table table;

    private Integer nrPersons;


    public Reservation(Client client, String date, Table table, Integer nrPersons) {
        this.reservationID = ++counter;
        this.client = client;
        this.date = date;
        this.table = table;
        this.nrPersons=nrPersons;
    }
    @Override
    public String toString(){
        return "ID: " + reservationID + " | " + "Client: " + client.getName() + " | " + "Date: " + date + " | " + "Table nr: " + table.getTableId() + " | " + "Persons at table: " + nrPersons;
    }


    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Integer getNrPersons() {
        return nrPersons;
    }

    public void setNrPersons(Integer nrPersons) {
        this.nrPersons = nrPersons;
    }


}
