package org.example.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.example.dbconfig.ConnectionManager;
import org.example.model.Role;
import org.example.model.User;

/**
 * Created by fanat1kq on 04/02/2024.
 */
public class UserDAO {
    private final ConnectionManager connectionManager;

    public UserDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * login
     * @exception ParseException
     */
//    public  boolean login(String userName, String UserPassword) {
//        String sql = "SELECT * FROM app.users WHERE username = ? AND password = ?";
//        try ( Connection connection = ConnectionManager.getConnection();
//              PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, userName);
//            statement.setString(2, UserPassword);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                int count  = resultSet.getInt(1);
//                if (count > 0) {
//                    String role= resultSet.getString("role");
//                    String user= resultSet.getString("username");
//                    int idUser=resultSet.getInt("id");
//                    System.out.println("Авторизация успешна!");
//                    return true;
//                } else {
//                    System.out.println("Неверный логин или пароль.");
//                }
//
//            }
//        } catch (SQLException e) {
//            System.out.println("Ошибка аунтификации игрока: " + e.getMessage());
//        }
//        return false;
//    }

    /**
     * create user
     * @exception ParseException
     */

    public User createUser(User newUser) {
            String sql = "INSERT INTO app.users (username, password,role) VALUES (?, ?, ?)";
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newUser.getName());
                statement.setString(2, newUser.getPassword());
                statement.setString(3, String.valueOf(newUser.getRole()));
                statement.executeUpdate();
                return newUser;
            } catch (SQLException e) {
                System.out.println("Ошибка при регистрации игрока: " + e.getMessage());
            }
            return null;
    }
    /**
     *menu working with indications
     * @exception ParseException
     */
    public Optional<User> findByLogin(String login) {
        Optional<User> optionalUser = Optional.empty();
        String sql = "SELECT * FROM app.users  WHERE username=?";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalUser = Optional.of(getPlayerFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return optionalUser;
    }
    private User getPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        int id=resultSet.getInt("id");
        String login = resultSet.getString("username");
        String password = resultSet.getString("password");
        Role role = Role.valueOf(resultSet.getString("role"));

        user.setId(id);
        user.setName(login);
        user.setPassword(password);
        user.setRole(role);

        return user;
    }
    public List<User> findAll() {
        List<User> all = new ArrayList<>();
        String sql = "SELECT* FROM app.users";
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                all.add(getPlayerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return all;
    }
}
