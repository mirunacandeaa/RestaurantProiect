package Repository.JDBA;

import Model.Client;
import Model.Reservation;
import Model.Table;
import Repository.IReservationRepository;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * the repository takes data from the database and creates the crud functions for the reservations class
 */
public class JDBAReservationRepo implements IReservationRepository {
    private String url;
    private List<Reservation> reservationList;
    public JDBAReservationRepo(){
        url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=Restaurant;"
                + "user=guest;"
                + "password=1234;"
                + "encrypt=true;"
                + "trustServerCertificate=true;";
        reservationList = new ArrayList<>();
        Connection conn = null;
        JDBAClientRepo clientRepo = new JDBAClientRepo();
        JDBATableRepo tableRepo = new JDBATableRepo();
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Reservation;");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("reservationID");
                Date date = resultSet.getDate("reservationDate");
                int nrOfPersons = resultSet.getInt("nrOfPersons");
                int tableID = resultSet.getInt("tableID");
                int clientID = resultSet.getInt("clientID");
                Client client = clientRepo.getAll().stream().filter(c->clientID==c.getClientID()).findAny().orElse(null);
                String stringDate = date.toString();
                Table table = tableRepo.getAll().stream().filter(t->t.getTableId().equals(tableID)).findAny().orElse(null);
                Reservation reservation = new Reservation(client,stringDate,table,nrOfPersons);
                reservation.setReservationID(id);
                reservationList.add(reservation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * adds a certain reservation
     * @param reservation
     * @return
     */
    @Override
    public boolean add(Reservation reservation) {
        for(Reservation r : reservationList)
            if(r.equals(reservation))
                return false;
        Connection conn = null;
        int ok=0;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO Reservation(reservationID,reservationDate,nrOfPersons,tableID,clientID) VALUES("
                    +reservation.getReservationID()+",\'"+reservation.getDate()+"\',\'"+reservation.getNrPersons()+"\',\'"+reservation.getTable().getTableId()+"\',\'"+reservation.getClient().getClientID()+"\');";
            PreparedStatement statement = conn.prepareStatement(sql);
            if(statement.executeUpdate()==1)
                ok=1;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        reservationList.add(reservation);
        return ok != 0;
    }

    /**
     * deletes a certain reservation
     * @param integer
     * @return
     */
    @Override
    public boolean delete(Integer integer) {
        int ok = 0;
        for(Reservation r : reservationList)
            if (r.getReservationID().equals(integer)) {
                ok = 1;
                break;
            }
        if(ok==0)
            return false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "DELETE FROM Reservation WHERE reservationID="+integer;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        reservationList.removeIf(r -> r.getReservationID().equals(integer));
        return true;
    }

    /**
     * updates a certain reservation
     * @param integer
     * @param reservation
     * @return
     */
    @Override
    public boolean update(Integer integer, Reservation reservation) {
        int ok = 0;
        for(Reservation r : reservationList)
            if (r.getReservationID().equals(integer)) {
                ok = 1;
                break;
            }
        if(ok==0)
            return false;
        reservation.setReservationID(integer);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "UPDATE Reservation SET reservationDate=\'"
                    +reservation.getDate()+"\', nrOfPersons=\'"+reservation.getNrPersons()+"\', tableID=\'"
                    +reservation.getTable().getTableId()+"\', clientID=\'"+reservation.getClient().getClientID()+"\' WHERE reservationID="+integer;
            System.out.println(sql);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Reservation r : reservationList)
            if (r.getReservationID().equals(integer)) {
                r=reservation;
                break;
            }
        return true;
    }

    @Override
    public Reservation findbyId(Integer integer) {
        return null;
    }

    /**
     * gets a list with all the reservations
     * @return
     */
    @Override
    public List<Reservation> getAll() {
        return reservationList;
    }
}
