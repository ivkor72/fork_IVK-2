package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
//import jakarta.persistence.Entity;

import java.sql.*;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/users";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "IVK_student_kata-71";
    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;

    public static Connection getConnection() throws SQLException {
        try   {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Class.forName(DB_DRIVER);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            connection.setAutoCommit(true);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, DB_DRIVER);
        properties.put(Environment.URL, DB_URL);
        properties.put(Environment.USER, DB_USERNAME);
        properties.put(Environment.PASS, DB_PASSWORD);
        try {
            sessionFactory = new Configuration()
                    .setProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) sessionFactory.close();
    }
}
