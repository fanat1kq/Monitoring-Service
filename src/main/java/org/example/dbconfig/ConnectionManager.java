package org.example.dbconfig;

import lombok.SneakyThrows;


import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Class ConnectionManager use for connection with DB
 */

public class ConnectionManager {

    private static String URL = Config.getUrl();
    private static String USERNAME = Config.getUsername();
    private static String PASSWORD = Config.getPassword();
    private static String DRIVER = Config.getDriver();

    public ConnectionManager(String url, String username, String password, String driver) {
        URL = url;
        USERNAME = username;
        PASSWORD = password;
        DRIVER = driver;
    }
@SneakyThrows
    public static Connection getConnection() {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }
}