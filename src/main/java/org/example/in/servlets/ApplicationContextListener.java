package org.example.in.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.dao.ReadingDAO;
import org.example.dao.UserDAO;
import org.example.dbconfig.ConnectionManager;
import org.example.in.mappers.IndicationsMapper;
import org.example.in.mappers.UserMapper;
import org.example.liquibase.Liquibase;
import org.example.service.ReadingService;
import org.example.service.SecurityService;
import org.example.service.UserService;



import org.example.in.security.JwtTokenProvider;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    private Properties properties;
    private ConnectionManager connectionManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();

        loadProperties(servletContext);
        databaseConfiguration(servletContext);
        serviceContextInit(servletContext);

        ObjectMapper jacksonMapper = new ObjectMapper();
        servletContext.setAttribute("jacksonMapper", jacksonMapper);
        servletContext.setAttribute("userMapper", UserMapper.INSTANCE);
        servletContext.setAttribute("transactionMapper", IndicationsMapper.INSTANCE);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

    private void databaseConfiguration(ServletContext servletContext) {
        String dbUrl = properties.getProperty("db.url");
        String dbUser = properties.getProperty("db.user");
        String dbPassword = properties.getProperty("db.password");
        String dbDriver = properties.getProperty("db.driver");
        connectionManager = new ConnectionManager(dbUrl, dbUser, dbPassword, dbDriver);
        servletContext.setAttribute("connectionProvider", connectionManager);

//        String changeLogFile = properties.getProperty("liquibase.changeLogFile");
//        String schemaName = properties.getProperty("liquibase.schemaName");

//        DBMigrationService migrationService = new DBMigrationService(connectionManager, schemaName, changeLogFile);
//        migrationService.migration();
        Liquibase migrationService=new Liquibase();
        servletContext.setAttribute("migrationService", migrationService);
    }

    private void serviceContextInit(ServletContext servletContext) {
        UserDAO userDAO = new UserDAO(connectionManager);
        ReadingDAO readingDAO = new ReadingDAO(connectionManager);

        UserService userService = new UserService(userDAO);
        JwtTokenProvider tokenProvider = new JwtTokenProvider(
                properties.getProperty("jwt.secret"),
                Long.parseLong(properties.getProperty("jwt.access")),
                Long.parseLong(properties.getProperty("jwt.refresh")),
                userService
        );

        SecurityService securityService = new SecurityService(userDAO, tokenProvider);
        ReadingService readingService = new ReadingService(readingDAO, userService);


        servletContext.setAttribute("tokenProvider", tokenProvider);
        servletContext.setAttribute("securityService", securityService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("readingService", readingService);
    }

    private void loadProperties(ServletContext servletContext) {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(servletContext.getResourceAsStream("/WEB-INF/classes/application.properties"));
                servletContext.setAttribute("servletProperties", properties);
            }catch (FileNotFoundException e ) {
                throw new RuntimeException("Property file not found!");
            } catch (IOException e) {
                throw new RuntimeException("Error reading configuration file: " + e.getMessage());
            }
        }
    }
}
