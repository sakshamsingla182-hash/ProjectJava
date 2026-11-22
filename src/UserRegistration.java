
import java.sql.*;
import java.util.Scanner;

class UserRegistration {

    public static void register(Connection conn, Scanner sc) {

        try {
            System.out.println("\n----- USER REGISTRATION -----");

            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            System.out.print("Enter your email: ");
            String email = sc.nextLine();

            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Registration Successful!");
            }

            pst.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("This email is already registered!");

        } catch (SQLException e) {
            System.out.println("Registration Failed!");
            System.out.println(e.getMessage());
        }
    }
}
