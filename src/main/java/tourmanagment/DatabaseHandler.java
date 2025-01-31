package com.example.tourmanagement;

import java.sql.*;

public class DatabaseHandler {
    private static DatabaseHandler instance;
    private Connection connection;

    private DatabaseHandler() {
        try {
            //загружаем драйвер PostgreSQL
            Class.forName("org.postgresql.Driver");

            //подключ к бд PostgreSQL
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/tour_management",
                    "postgres",
                    "Lasto4ka22"
            );

            connection.setAutoCommit(false);
            System.out.println(" Database connected successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println(" PostgreSQL JDBC Driver not found! Make sure it's in your dependencies.");
        } catch (SQLException e) {
            System.out.println(" Database connection failed: " + e.getMessage());
        }
    }

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
