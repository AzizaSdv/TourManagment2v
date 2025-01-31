package com.example.tourmanagement;

import java.sql.*;

public class Admin {
    private Connection connection;

    public Admin() {
        this.connection = DatabaseHandler.getInstance().getConnection();
    }

    //метод для добавл тура в бд
    public void addTour(Tour tour) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO tours (name, location, price, dates) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, tour.getName());
            pstmt.setString(2, tour.getLocation());
            pstmt.setDouble(3, tour.getPrice());
            pstmt.setString(4, tour.getDates());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    tour.setId(keys.getInt(1));
                    System.out.println("Tour added successfully with ID: " + tour.getId());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding tour: " + e.getMessage());
        }
    }

    //метод для просмотра всех туров
    public void viewAllTours() {
        try (Connection conn = DatabaseHandler.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tours")) {

            System.out.println("\n--- List of Tours ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                        ", Location: " + rs.getString("location") + ", Price: $" + rs.getDouble("price") +
                        ", Dates: " + rs.getString("dates"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tours: " + e.getMessage());
        }
    }

    // метод для удаления тура по ID
    public void deleteTour(int tourId) {
        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM tours WHERE id = ?")) {
            pstmt.setInt(1, tourId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Tour deleted successfully.");
            } else {
                System.out.println("Tour with ID " + tourId + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting tour: " + e.getMessage());
        }
    }

    // Метод для обновл инф о туре
    public void updateTour(Tour tour) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE tours SET name = ?, location = ?, price = ?, dates = ? WHERE id = ?")) {

            pstmt.setString(1, tour.getName());
            pstmt.setString(2, tour.getLocation());
            pstmt.setDouble(3, tour.getPrice());
            pstmt.setString(4, tour.getDates());
            pstmt.setInt(5, tour.getId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Tour updated successfully.");
            } else {
                System.out.println("Tour with ID " + tour.getId() + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating tour: " + e.getMessage());
        }
    }
}
