package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserDao userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Sergey", "Sergeev", (byte) 29);
        System.out.println("User for name 'Sergey' added to the database");
        userDaoJDBC.saveUser("Ivan", "Petrov", (byte) 32);
        System.out.println("User for name 'Ivan' added to the database");
        userDaoJDBC.saveUser("Igor", "Sidorov", (byte) 30);
        System.out.println("User for name 'Igor' added to the database");
        userDaoJDBC.saveUser("Dmitriy", "Ivanov", (byte) 25);
        System.out.println("User for name 'Dmitriy' added to the database");

        List<User> userList = userDaoJDBC.getAllUsers();
        System.out.println(userList);

        userDaoJDBC.cleanUsersTable();

        userDaoJDBC.dropUsersTable();


    }
}
