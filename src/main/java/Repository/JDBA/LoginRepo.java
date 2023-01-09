package Repository.JDBA;

import Model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginRepo {
    private List<String> usernameList;
    private List<String> passwordList;
    private String url;
    public LoginRepo(){
        url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=Restaurant;"
                + "user=guest;"
                + "password=1234;"
                + "encrypt=true;"
                + "trustServerCertificate=true;";
        usernameList = new ArrayList<>();
        passwordList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM login_credentials;");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("clientID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                usernameList.add(username);
                passwordList.add(password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getUsernameList(){
        return usernameList;
    }
    public List<String> getPasswordList(){
        return passwordList;
    }
}
