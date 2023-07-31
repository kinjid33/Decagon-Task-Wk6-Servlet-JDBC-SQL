package week6task.ecommercesite2.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Database class to facilitate connection to database
public class DBConnection {
    private static Connection connection = null;
    public static Connection getConnection(){
//        if there is no connection, create one
        if(connection == null){
            try {
//                db url
                String url = "jdbc:mysql://localhost:3306/ecommerce_project";
//                db username
                String userName = "root";
//                db password
                String password = "m0d@k3k3";
//                load jdbc driver class
                Class.forName("com.mysql.cj.jdbc.Driver");
//                establish connection to the database
                connection = DriverManager.getConnection(url, userName, password);
                System.out.println("connected");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
//        return connection
        return connection;
    }
}
