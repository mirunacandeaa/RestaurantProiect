package Model;

import java.util.Date;

public class Reservation {

    private Integer reservationID;
    private Client client;
    private Date date;
    private Table table;

    private Integer nrPersons;


    public Reservation(Integer reservationID, Client client, Date date, Table table, Integer nrPersons) {
        this.reservationID = reservationID;
        this.client = client;
        this.date = date;
        this.table = table;
        this.nrPersons=nrPersons;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
