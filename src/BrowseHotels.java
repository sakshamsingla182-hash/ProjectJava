import java.sql.*;
class BrowseHotels {

    public static void displayHotels(Connection conn) {

        try {
            String query = "SELECT hotels.hotel_id, hotels.hotel_name, " +
                           "cities.city_name, hotels.price_per_night, hotels.rating " +
                           "FROM hotels " +
                           "JOIN cities ON hotels.city_id = cities.city_id";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n----- AVAILABLE HOTELS -----");

            while (rs.next()) {
                System.out.println("\nHotel ID: " + rs.getInt("hotel_id"));
                System.out.println("Hotel Name: " + rs.getString("hotel_name"));
                System.out.println("City: " + rs.getString("city_name"));
                System.out.println("Price Per Night: Rs. " + rs.getDouble("price_per_night"));
                System.out.println("Rating: " + rs.getDouble("rating"));
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Unable to display hotels!");
            System.out.println(e.getMessage());
        }
    }
}