package org.example.liquibase;

import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * class for start Liquibase  which migration of DB
 * method startLiquibase(), do migrations
 */
public class Liquibase {
    public void start() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "nikita",
                    "postgres"

            );
            Statement statement = connection.createStatement();
            String sql = "CREATE SCHEMA IF NOT EXISTS " + "migration";
            statement.executeUpdate(sql);
            statement.close();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setDefaultSchemaName("migration");
            liquibase.Liquibase liquibase = new liquibase.Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.out.println("Миграции успешно выполнены!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}