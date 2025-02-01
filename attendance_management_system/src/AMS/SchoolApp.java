package AMS;
import java.util.Scanner;

public class SchoolApp {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n🏫 School Attendance Management System");
            System.out.println("1️ Add Student");
            System.out.println("2️ Display Students");
            System.out.println("3️ Mark Attendance");
            System.out.println("4️ View Attendance Records");
            System.out.println("5️ Check Attendance Percentage");
            System.out.println("6️ Delete Student");
            System.out.println("7 Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Class: ");
                    String studentClass = scanner.nextLine();
                    studentDAO.addStudent(name, studentClass);
                    break;

                case 2:
                    studentDAO.displayStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();
                    System.out.print("Enter Status (Present/Absent): ");
                    String status = scanner.next();
                    attendanceDAO.markAttendance(studentId, status);
                    break;

                case 4:
                    attendanceDAO.viewAttendance();
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    attendanceDAO.checkAttendancePercentage(id);
                    break;
                    
                case 6:
                    System.out.print("Enter Student ID to delete: ");
                    int studentid = scanner.nextInt();
                    studentDAO.deleteStudent(studentid);
                    break;

                case 7:
                    System.out.println("🚀 Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid Choice! Try Again.");
            }
        }
    }
}
