package Repository.JDBA;

import Model.Client;
import Model.Table;
import Model.Waiter;
import Repository.IWaiterRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBAWaiterRepo implements IWaiterRepository {

    private String url;
    private List<Waiter> waiterList;
    public JDBAWaiterRepo()
    {
        url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=Restaurant;"
                + "user=guest;"
                + "password=1234;"
                + "encrypt=true;"
                + "trustServerCertificate=true;";
        waiterList = new ArrayList<>();

        Connection conn=null;
        try{
            conn=DriverManager.getConnection(url);
            PreparedStatement statement=conn.prepareStatement("SELECT * FROM Waiter");
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next())
            {
                int id=resultSet.getInt("waiterID");
                String firstName=resultSet.getString("firstName");
                String lastName=resultSet.getString("lastName");
                Waiter waiter=new Waiter(firstName, lastName, id, new ArrayList<>());
                waiterList.add(waiter);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(Waiter waiter) {
        for(Waiter c : waiterList)
            if(c.equals(waiter))
                return false;

        int ok=0;
        Connection conn=null;
        try{

            conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO Waiter(waiterID,firstName,lastName) VALUES("
                    +waiter.getWaiterID()+",\'"+waiter.getFirstName()+"\',\'"+waiter.getLastName()+"\');";
            PreparedStatement statement=conn.prepareStatement(sql);
            if(statement.executeUpdate()==1)
            {
                ok=1;
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }


        waiterList.add(waiter);
        return ok!=0;
    }

    @Override
    public boolean delete(Integer integer) {
        int ok = 0;
        for(Waiter w: waiterList)
            if (w.getWaiterID().equals(integer)) {
                ok = 1;
                break;
            }
        if(ok==0)
            return false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "DELETE FROM Waiter WHERE waiterID="+integer;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        waiterList.removeIf(w->w.getWaiterID().equals(integer));
        return true;
    }

    @Override
    public boolean update(Integer integer, Waiter waiter) {
        int ok = 0;
        for(Waiter w:waiterList)
            if (w.getWaiterID().equals(integer)) {
                ok = 1;
                break;
            }
        if(ok==0)
            return false;
        waiter.setWaiterID(integer);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "UPDATE Waiter SET firstName=\'"
                    +waiter.getFirstName()+"\', lastName=\'"+waiter.getLastName()+ "\' WHERE waiterID="+integer;
            System.out.println(sql);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Waiter w : waiterList)
            if (w.getWaiterID().equals(integer)) {
                w=waiter;
                break;
            }
        return true;
    }

    @Override
    public Waiter findbyId(Integer integer) {
        for(Waiter w:waiterList)
            if(w.getWaiterID().equals(integer))
                return w;
        return null;
    }

    @Override
    public List<Waiter> getAll() {
        return waiterList;
    }

    public void actualiseWaitersAtTables(){
        JDBATableRepo tableRepo = new JDBATableRepo();
        Connection conn=null;
        try{
            conn=DriverManager.getConnection(url);
            PreparedStatement statement=conn.prepareStatement("SELECT * FROM WaitersAtTables");
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next())
            {
                int tableID=resultSet.getInt("tableID");
                int waiterID=resultSet.getInt("waiterID");
                Table table = tableRepo.getAll().stream().filter(t->t.getTableId().equals(tableID)).findAny().orElse(null);
                waiterList.stream().filter(w->w.getWaiterID().equals(waiterID)).findAny().get().getTableList().add(table);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Table> getTablesForWaiter(Integer ID) {
        for(Waiter w: waiterList)
        {
            if(w.getWaiterID().equals(ID))
            {
                return w.getTableList();
            }
        }
        return null;
    }


    @Override
    public void setTableForWaiter(Waiter waiter, Table table) {
        for(Waiter w: waiterList)
        {
            if(w.equals(waiter))
            {
                w.getTableList().add(table);
            }
        }
    }

}
