import java.sql.*;
import java.util.Scanner;

class TripPlan {

    public static void createPlan(Connection conn, Scanner sc, int userId) {

        try {
            System.out.println("\n----- AVAILABLE CITIES -----");

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cities");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("city_id") + ". " +
                    rs.getString("city_name")
                );
            }

            rs.close();
            st.close();

            System.out.print("\nEnter city ID: ");
            int cityId = sc.nextInt();

            System.out.print("Enter number of travellers: ");
            int travelers = sc.nextInt();

            System.out.print("Enter number of days: ");
            int days = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter transport mode: ");
            String transport = sc.nextLine();

            System.out.print("Enter accommodation type: ");
            String accommodation = sc.nextLine();

            System.out.print("Enter food preference: ");
            String food = sc.nextLine();

            System.out.print("Enter estimated budget: ");
            double budget = sc.nextDouble();
            sc.nextLine();

            String query = "INSERT INTO trip_plans " +
                "(user_id, city_id, travelers, days, transport_mode, " +
                "accommodation_tier, food_tier, estimated_total) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(query);

            pst.setInt(1, userId);
            pst.setInt(2, cityId);
            pst.setInt(3, travelers);
            pst.setInt(4, days);
            pst.setString(5, transport);
            pst.setString(6, accommodation);
            pst.setString(7, food);
            pst.setDouble(8, budget);

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("\nTrip Plan Created Successfully!");
            }

            pst.close();

        } catch (SQLException e) {
            System.out.println("Unable to create trip plan!");
            System.out.println(e.getMessage());
        }
    }
}
