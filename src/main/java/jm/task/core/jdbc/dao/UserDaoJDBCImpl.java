package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = null;

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users.users_table(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age VARCHAR(45) NOT NULL)";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            int resultSet = statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE users. users_table";
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO users.users_table (id, name, lastName, age) VALUES (default, ?, ?, ?)";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users.users_table WHERE id = ?";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List <User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users.users_table";
        try (Connection connection = Util.getConnection();Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(Byte.valueOf(resultSet.getString("age")));
                userList.add(user);
                connection.commit();
            }
        } catch (SQLException e) {
            connection.rollback();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM users.users_table";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            int resultSet = statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
