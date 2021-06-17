package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String table = "CREATE TABLE user (" +
                "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50) NOT NULL," +
                "age INT NOT NULL)";

        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String table = "DROP TABLE IF EXISTS user";

        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String table = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(table);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String table = "DELETE FROM user WHERE id = ?";
        try(Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(table);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        String table = "SELECT * FROM user";
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(table);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        String table = "DELETE FROM user";
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
