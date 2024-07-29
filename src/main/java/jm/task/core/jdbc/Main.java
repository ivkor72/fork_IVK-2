package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sergey", "Sergeev", (byte) 29);
        userService.saveUser("Ivan", "Petrov", (byte) 32);
        userService.saveUser("Igor", "Sidorov", (byte) 30);
        userService.saveUser("Dmitriy", "Ivanov", (byte) 25);
        List<User> userList = userService.getAllUsers();
        System.out.println(userList);
        userService.cleanUsersTable();
        userService.dropUsersTable();
 //       Util.closeConnection();
        Util.closeSessionFactory();
    }
}
