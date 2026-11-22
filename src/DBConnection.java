
import java.sql.*;

class DBConnection {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/voyage_verse_java";
        String username = "root";
        String password = "Manshu@18";

        return DriverManager.getConnection(url, username, password);
    }
}
