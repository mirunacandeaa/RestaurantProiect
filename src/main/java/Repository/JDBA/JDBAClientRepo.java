package Repository.JDBA;

import Model.Client;
import Model.Reservation;
import Repository.IClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * the repository takes data from the database and creates the crud functions for the client class
 */
public class JDBAClientRepo implements IClientRepository {

    private String url;
    private List<Client> clientList;
    public JDBAClientRepo() {
        url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=Restaurant;"
                + "user=guest;"
                + "password=1234;"
                + "encrypt=true;"
                + "trustServerCertificate=true;";
        clientList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Client;");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("clientID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phone = resultSet.getString("phone");
                Client client = new Client(firstName,lastName,id,phone,new ArrayList<>());
                clientList.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //this.addReservations();
    }

    /**
     * adds a new client
     * @param client
     * @return
     */
    @Override
    public boolean add(Client client) {
        for(Client c : clientList)
            if(c.equals(client))
                return false;
        Connection conn = null;
        int ok=0;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO Client(clientID,firstName,lastName,phone) VALUES("
                    +client.getClientID()+",\'"+client.getFirstName()+"\',\'"+client.getLastName()+"\',\'"+client.getPhone()+"\');";
            PreparedStatement statement = conn.prepareStatement(sql);
            if(statement.executeUpdate()==1)
                ok=1;
        }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        clientList.add(client);
        return ok != 0;
    }

    /**
     * delete a certaing client
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        int ok = 0;
        for(Client c : clientList)
            if (c.getClientID().equals(id)) {
                ok = 1;
                break;
            }
        if(ok==0)
            return false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "DELETE FROM Client WHERE clientID="+id;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clientList.removeIf(c -> c.getClientID().equals(id));
        return true;
    }

    /**
     * update a certain client
     * @param id
     * @param client
     * @return
     */
    @Override
    public boolean update(Integer id, Client client) {
        int ok = 0;
        for(Client c : clientList)
            if (c.getClientID().equals(id)) {
                ok = 1;
                break;
            }
        if(ok==0)
            return false;
        client.setClientID(id);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "UPDATE Client SET firstName=\'"
                    +client.getFirstName()+"\', lastName=\'"+client.getLastName()+"\', phone=\'"
                    +client.getPhone()+"\' WHERE clientID="+id;
            System.out.println(sql);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Client c : clientList)
            if (c.getClientID().equals(id)) {
                c=client;
                break;
            }
        return true;
    }

    /**
     * find a certain client
     * @param integer
     * @return
     */
    @Override
    public Client findbyId(Integer integer) {
        for(Client c : clientList)
            if(c.getClientID().equals(integer))
                return c;
        return null;
    }

    public void addReservations(){
        JDBAReservationRepo r = new JDBAReservationRepo();
        /*r.getAll().stream().forEach(res->{
            clientList.stream().filter(c->c.getClientID().equals(res.getClient().getClientID())).findAny().get().getReservationList().add(res);
        });*/
        for(Reservation res : r.getAll()){
            for(Client c : clientList){
                if(res.getClient().getClientID().equals(c.getClientID())){
                    c.getReservationList().add(res);
                }
            }
        }
    }

    /**
     * returns all the clients
     * @return
     */
    @Override
    public List<Client> getAll() {
        return clientList;
    }
}
