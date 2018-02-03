package dao.implementation;

import dao.DatabaseConnection;
import dao.GenericDAO;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements GenericDAO<User, Integer> {
    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while ((resultSet.next())) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int update(User entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USER " +
                     "SET LOGIN = ?, PASSWORD = ? " +
                     "WHERE ID = ?")) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setInt(1, entity.getId());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public User getEntityById(Integer id) {
        User user = new User();
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while ((resultSet.next())) {
                    user.setId(resultSet.getInt(1));
                    user.setLogin(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int delete(Integer id) {
        int affectedRowsAmount = 0;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE USER WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }

    @Override
    public int create(User entity) {
        int affectedRowsAmount = 0;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USER " +
                     "(LOGIN, PASSWORD) VALUES (?, ?)")) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            affectedRowsAmount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRowsAmount;
    }
}