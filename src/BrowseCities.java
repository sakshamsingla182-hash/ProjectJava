import java.sql.*;

class BrowseCities {

    public static void displayCities(Connection conn) {

        try {
            String query = "SELECT cities.city_name, states.state_name " +
                           "FROM cities INNER JOIN states " +
                           "ON cities.state_id = states.state_id";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            System.out.println("\n----- AVAILABLE CITIES -----");

            while (rs.next()) {
                System.out.println(
                    rs.getString("city_name") + " - " +
                    rs.getString("state_name")
                );
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Unable to display cities!");
            System.out.println(e.getMessage());
        }
    }
}
