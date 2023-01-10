package Repository.JDBA;

import Model.Client;
import Model.Table;
import Model.Waiter;
import Repository.ITableRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * the repository takes data from the database and creates the crud functions for the tables class
 */
public class JDBATableRepo implements ITableRepository {
    private String url;
    private List<Table> tableList;

    public JDBATableRepo(){
        url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=Restaurant;"
                + "user=guest;"
                + "password=1234;"
                + "encrypt=true;"
                + "trustServerCertificate=true;";
        tableList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM [Table];");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("tableID");
                int nrOfPersons = resultSet.getInt("nrOfPersons");
                Table table = new Table(id,nrOfPersons,new ArrayList<>());
                tableList.add(table);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Table table) {
        for(Table t : tableList)
            if(t.equals(table))
                return false;
        Connection conn = null;
        int ok=0;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO Table(tableID,nrOfPersons) VALUES("
                    +table.getTableId()+",\'"+table.getNrPersons()+"\';";
            PreparedStatement statement = conn.prepareStatement(sql);
            if(statement.executeUpdate()==1)
                ok=1;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableList.add(table);
        return ok != 0;
    }

    @Override
    public boolean delete(Integer integer) {
        int ok = 0;
        for(Table t : tableList)
            if (t.getTableId().equals(integer)) {
                ok = 1;
                break;
            }
        if(ok==0)
            return false;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "DELETE FROM Table WHERE tableID="+integer;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableList.removeIf(c -> c.getTableId().equals(integer));
        return true;
    }

    @Override
    public boolean update(Integer integer, Table table) {
        int ok = 0;
        for(Table t : tableList)
            if (t.getTableId().equals(integer)) {
                ok = 1;
                break;
            }
        if(ok==0)
            return false;
        table.setTableId(integer);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "UPDATE Table SET nrOfPersons=\'"
                    +table.getNrPersons()+"\' WHERE clientID="+integer;
            System.out.println(sql);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Table t : tableList)
            if (t.getTableId().equals(integer)) {
                t=table;
                break;
            }
        return true;
    }

    @Override
    public Table findbyId(Integer integer) {
        for(Table t : tableList)
            if(t.getTableId().equals(integer))
                return t;
        return null;
    }

    @Override
    public List<Table> getAll() {
        return tableList;
    }

    public void actualiseWaitersAtTables(){
        JDBAWaiterRepo jdbaWaiterRepo = new JDBAWaiterRepo();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM WaitersAtTables;");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int tableID = resultSet.getInt("tableID");
                int waiterID = resultSet.getInt("waiterID");
                Waiter waiter = jdbaWaiterRepo.getAll().stream().filter(w->waiterID==w.getWaiterID()).findAny().orElse(null);
                tableList.stream().filter(table -> tableID==table.getTableId()).findAny().get().getWaiterList().add(waiter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * gets the waiters from a given table
     * @param ID
     * @return
     */
    @Override
    public List<Waiter> getWaitersAtTable(Integer ID) {
        return tableList.stream().filter(t->t.getTableId().equals(ID)).findAny().get().getWaiterList();
    }

    /**
     * sets the waiters at a given table
     * @param waiter
     * @param table
     */
    @Override
    public void setWaiterListAtTable(Waiter waiter, Table table) {
        for(Table t: tableList)
        {
            if(t.equals(table))
            {
                t.getWaiterList().add(waiter);
            }
        }
        Connection conn = null;
        int ok=0;
        try {
            conn = DriverManager.getConnection(url);
            String sql = "INSERT INTO WaitersAtTables(tableID,waiterID) VALUES("
                    +table.getTableId()+","+waiter.getWaiterID()+");";
            PreparedStatement statement = conn.prepareStatement(sql);
            if(statement.executeUpdate()==1)
                ok=1;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
