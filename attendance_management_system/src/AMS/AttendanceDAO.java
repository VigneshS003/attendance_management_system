package AMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceDAO {

    // Mark Attendance
    public void markAttendance(int studentId, String status) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection error.");
            return;
        }

        String sql = "INSERT INTO attendance (student_id, status) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setString(2, status);
            stmt.executeUpdate();
            System.out.println("✅ Attendance marked successfully!");
            Thread.sleep(500);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // View Attendance Records
    public void viewAttendance() {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection error.");
            return;
        }

        String sql = "SELECT students.name, students.class, attendance.date, attendance.status " +
                     "FROM students INNER JOIN attendance ON students.id = attendance.student_id";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n📊 Attendance Records:");
            while (rs.next()) {
                System.out.println("📌 " + rs.getString("name") + " | Class: " + rs.getString("class") +
                        " | Date: " + rs.getDate("date") + " | Status: " + rs.getString("status"));
                Thread.sleep(300);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Check Student Attendance Percentage
    public void checkAttendancePercentage(int studentId) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection error.");
            return;
        }

        String sql = "SELECT COUNT(*) AS total, " +
                     "SUM(CASE WHEN status = 'Present' THEN 1 ELSE 0 END) AS present " +
                     "FROM attendance WHERE student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int total = rs.getInt("total");
                int present = rs.getInt("present");
                if (total > 0) {
                    double percentage = (present / (double) total) * 100;
                    System.out.printf("📈 Attendance Percentage: %.2f%%\n", percentage);
                } else {
                    System.out.println("⚠️ No attendance records found!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
