package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection;

    public UserDaoJDBCImpl() throws SQLException {
        connection = Util.getConnection();
    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users.users_table(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age VARCHAR(45) NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            int resultSet = statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException exc) {
            exc.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE users. users_table";
            try (Statement statement = connection.createStatement()) {
                int resultSet = statement.executeUpdate(sql);
                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }


    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO users.users_table (id, name, lastName, age) VALUES (default, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                connection.commit();
                System.out.println("User for name " + name + " added to the database");
            } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users.users_table WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List <User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users.users_table";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(Byte.valueOf(resultSet.getString("age")));
                    userList.add(user);
                    connection.commit();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM users.users_table";
            try (Statement statement = connection.createStatement()) {
                int resultSet = statement.executeUpdate(sql);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
