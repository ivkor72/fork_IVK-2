package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sergey", "Sergeev", (byte) 29);
        System.out.println("User for name 'Sergey' added to the database");
        userService.saveUser("Ivan", "Petrov", (byte) 32);
        System.out.println("User for name 'Ivan' added to the database");
        userService.saveUser("Igor", "Sidorov", (byte) 30);
        System.out.println("User for name 'Igor' added to the database");
        userService.saveUser("Dmitriy", "Ivanov", (byte) 25);
        System.out.println("User for name 'Dmitriy' added to the database");
        List<User> userList = userService.getAllUsers();
        System.out.println(userList);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
