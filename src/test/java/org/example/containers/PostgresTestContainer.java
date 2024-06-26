package org.example.containers;//package org.example.containers;
//
//import org.testcontainers.containers.PostgreSQLContainer;
//
//public class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {
//    public static final String IMAGE_VERSION = "postgres:13.3";
//    public static final String DATABASE_NAME = "nikita";
//    public static PostgreSQLContainer container;
//
//    public PostgresTestContainer() {
//        super(IMAGE_VERSION);
//    }
//
//    public static PostgreSQLContainer getInstance() {
//        if (container == null) {
//            container = new PostgresTestContainer().withDatabaseName(DATABASE_NAME);
//        }
//        return container;
//    }
//
//    @Override
//    public void start() {
//        super.start();
//        System.setProperty("DB_URL", container.getJdbcUrl());
//        System.setProperty("DB_USERNAME", container.getUsername());
//        System.setProperty("DB_PASSWORD", container.getPassword());
//    }
//
//    @Override
//    public void stop() {
//    }
//}