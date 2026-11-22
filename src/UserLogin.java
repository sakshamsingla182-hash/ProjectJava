
import java.sql.*;
import java.util.Scanner;

class UserLogin {

    static int userId = 0;

    public static boolean login(Connection conn, Scanner sc) {

        try {
            System.out.println("\n----- USER LOGIN -----");

            System.out.print("Enter your email: ");
            String email = sc.nextLine();

            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            String query = "SELECT * FROM users WHERE email = ? AND password = ?";

            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("user_id");

                System.out.println("Login Successful!");
                System.out.println("Welcome, " + rs.getString("name"));

                rs.close();
                pst.close();
                return true;
            } else {
                System.out.println("Invalid email or password!");
            }

            rs.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
