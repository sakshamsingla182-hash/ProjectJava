import java.sql.*;
class DestinationDetails {
    public static void displayDetails(Connection conn) {
        try {
            String query = "SELECT attractions.attraction_name, " +
                           "cities.city_name, states.state_name, " +
                           "attractions.description, attractions.experience " +
                           "FROM attractions " +
                           "INNER JOIN cities ON attractions.city_id = cities.city_id " +
                           "INNER JOIN states ON cities.state_id = states.state_id";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            System.out.println("\n----- DESTINATION DETAILS -----");

            while (rs.next()) {

                System.out.println("\nAttraction: " +
                    rs.getString("attraction_name"));

                System.out.println("City: " +
                    rs.getString("city_name"));

                System.out.println("State: " +
                    rs.getString("state_name"));

                System.out.println("Description: " +
                    rs.getString("description"));

                System.out.println("Experience: " +
                    rs.getString("experience"));

                System.out.println("------------------------------");
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Unable to display destination details!");
            System.out.println(e.getMessage());
        }
    }
}
