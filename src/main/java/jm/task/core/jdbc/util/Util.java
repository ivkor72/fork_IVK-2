package jm.task.core.jdbc.util;
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
import java.sql.*;




import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/users";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "IVK_student_kata-71";


 //

    public Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        try (connection)  {
            Class.forName(DB_DRIVER);
            System.out.println("Connection is successful");
        } catch (ClassNotFoundException | SQLException e) {
   //         e.printStackTrace();
           System.out.println("Connection failed");
        }

        return connection;
    }

} //util
