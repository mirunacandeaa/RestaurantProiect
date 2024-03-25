package Repository.JDBA;

import javassist.compiler.ast.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * the repository takes data from the database and creates the crud functions for the logs
 */
//public class JDBALoginRepo {
//    private Map<String, String> loginCredentials;
//    private String url;
//    public JDBALoginRepo(){
//        url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=Restaurant;"
//                + "user=guest;"
//                + "password=1234;"
//                + "encrypt=true;"
//                + "trustServerCertificate=true;";
//        loginCredentials = new HashMap<>();
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url);
//            PreparedStatement statement = conn.prepareStatement("SELECT * FROM login_credentials;");
//            ResultSet resultSet = statement.executeQuery();
//            while(resultSet.next()){
//                String username = resultSet.getString("username");
//                String password = resultSet.getString("password");
//                loginCredentials.put(username,password);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public Map<String,String> getLoginCredentials(){
//        return loginCredentials;
//    }
//}
