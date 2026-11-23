import java.sql.*;
import java.util.Scanner;
class AddToTripBucket {
    public static void addDestination(Connection conn, Scanner sc, int userId) {
        try {
            System.out.println("\n----- AVAILABLE CITIES -----");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cities");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("city_id") + ". " + rs.getString("city_name")
                );
            }
            rs.close();
            st.close();
            System.out.print("\nEnter city ID to add: ");
            int cityId = sc.nextInt();
            sc.nextLine();
            String query = "INSERT INTO trip_bucket (user_id, city_id) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, cityId);
            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Destination added to Trip Bucket!");
            }
            pst.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("This destination is already in your Trip Bucket!");

        } catch (SQLException e) {
            System.out.println("Unable to add destination!");
            System.out.println(e.getMessage());
        }
    }
}