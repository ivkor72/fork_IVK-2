package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }



    public void createUsersTable() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS users. users_table(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age VARCHAR(45) NOT NULL)";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("The table 'users_table' has been created");
        } catch (SQLException e) {
            System.out.println("The table has not been created!");
        }


    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE users. users_table";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("The table 'users_table' has been deleted");
        } catch (SQLException e) {
            System.out.println("The table 'users_table' has not been deleted!");
        }
    }
    public void saveUser(String name, String lastName, byte age) throws SQLException {


        String sql = "INSERT INTO users_table (id, name, lastName, age) VALUES (default, ?, ?, ?)";


        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users_table WHERE id = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List <User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users_table";

        try (Connection connection = getConnection();Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users_table";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

        } catch (SQLException e) {

        }
    }

}
