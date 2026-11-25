import java.sql.*;
import java.util.Scanner;

class HotelBooking {

    public static void bookHotel(Connection conn, Scanner sc, int userId) {

        try {
            BrowseHotels.displayHotels(conn);

            System.out.print("\nEnter Hotel ID: ");
            int hotelId = sc.nextInt();

            System.out.print("Enter number of rooms: ");
            int rooms = sc.nextInt();

            System.out.print("Enter number of guests: ");
            int guests = sc.nextInt();

            String query = "INSERT INTO hotel_bookings " +
                    "(user_id, hotel_id, check_in, check_out, rooms, guests, total_amount, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(query);

            pst.setInt(1, userId);
            pst.setInt(2, hotelId);
            pst.setDate(3, Date.valueOf("2026-12-01"));
            pst.setDate(4, Date.valueOf("2026-12-03"));
            pst.setInt(5, rooms);
            pst.setInt(6, guests);
            pst.setDouble(7, 3000 * rooms);
            pst.setString(8, "Confirmed");

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Hotel Booked Successfully!");
                System.out.println("Total Amount: Rs. " + (3000 * rooms));
            }

            pst.close();

        } catch (SQLException e) {
            System.out.println("Booking Failed!");
            System.out.println(e.getMessage());
        }
    }
}