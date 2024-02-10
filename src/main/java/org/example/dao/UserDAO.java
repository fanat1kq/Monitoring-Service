package org.example.dao;

import java.sql.*;
import java.text.ParseException;


import org.example.dbconfig.ConnectionManager;
import org.example.in.AppConsole;

/**
 * Created by fanat1kq on 04/02/2024.
 */
public class UserDAO {
AppConsole appConsole=new AppConsole();
    /**
     * login
     * @exception ParseException
     */
    public  boolean login(String userName, String UserPassword) {
        String sql = "SELECT * FROM app.users WHERE username = ? AND password = ?";
        try ( Connection connection = ConnectionManager.getConnection();
              PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setString(2, UserPassword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count  = resultSet.getInt(1);
                if (count > 0) {
                    String role= resultSet.getString("role");
                    String user= resultSet.getString("username");
                    int idUser=resultSet.getInt("id");
                    System.out.println("Авторизация успешна!");
                    appConsole.AppLoop(user,role,idUser);
                    return true;
                } else {
                    System.out.println("Неверный логин или пароль.");
                }

            }
        } catch (SQLException e) {
            System.out.println("Ошибка аунтификации игрока: " + e.getMessage());
        }
        return false;
    }

    /**
     * create user
     * @exception ParseException
     */

    public void createUser(String userName, String userPassword, String userRole) {
            String sql = "INSERT INTO app.users (username, password,role) VALUES (?, ?, ?)";
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userName);
                statement.setString(2, userPassword);
                statement.setString(3, userRole);
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Ошибка при регистрации игрока: " + e.getMessage());
            }


    }
    /**
     *menu working with indications
     * @exception ParseException
     */
}
