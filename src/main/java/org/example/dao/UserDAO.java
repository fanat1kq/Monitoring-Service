package org.example.dao;

import org.example.enumerates.Role;
import org.example.model.User;

import java.sql.*;
import java.text.ParseException;
import java.util.HashMap;

import static org.example.In.AppConsole.*;
import org.example.dbConfig.ConnectionManager;
/**
 * Created by fanat1kq on 04/02/2024.
 */
public class UserDAO {
    static HashMap<User, Role> map=new HashMap<>();
    /**
     * login
     * @exception ParseException
     */
    public static boolean login(String userName, String UserPassword) {
        String sql = "SELECT * FROM migration.users WHERE username = ? AND password = ?";
        try ( Connection connection = ConnectionManager.getConnection();
              PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setString(2, UserPassword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count  = resultSet.getInt(1);
                if (count > 0) {
                    String role= resultSet.getString("role");
                    System.out.println("Авторизация успешна!");
                    AppLoop(role);
                    return true;
                } else {
                    System.out.println("Неверный логин или пароль.");
                }

            }
        } catch (SQLException e) {
            System.out.println("Ошибка аунтификации игрока: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * create user
     * @exception ParseException
     */

    public static void createUser(String userName, String userPassword, String userRole) {
            String sql = "INSERT INTO migration.users (username, password,role) VALUES (?, ?, ?)";
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
