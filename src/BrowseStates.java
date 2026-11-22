import java.sql.*;

class BrowseStates {

    public static void displayStates(Connection conn) {

        try {
            String query = "SELECT * FROM states";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            System.out.println("\n----- AVAILABLE INDIAN STATES -----");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("state_id") + ". " +
                    rs.getString("state_name")
                );
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Unable to display states!");
            System.out.println(e.getMessage());
        }
    }
}
