package AMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    // Add a new student
    public void addStudent(String name, String studentClass) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection failed.");
            return;
        }

        String sql = "INSERT INTO students (name, class) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, studentClass);
            stmt.executeUpdate();
            System.out.println("✅ Student added successfully!");
            Thread.sleep(500);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
 // Delete Student Method
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Student deleted successfully!");
            } else {
                System.out.println("❌ No student found with ID: " + studentId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Display all students
    public void displayStudents() {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection error.");
            return;
        }

        String sql = "SELECT * FROM students";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n📋 List of Students:");
            while (rs.next()) {
                System.out.println("🆔 ID: " + rs.getInt("id") + " | Name: " + rs.getString("name") +
                        " | Class: " + rs.getString("class"));
                Thread.sleep(300);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
