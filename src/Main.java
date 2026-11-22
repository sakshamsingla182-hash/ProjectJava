import java.sql.*;
import java.util.Scanner;
class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;

        try {
            Connection conn = DBConnection.getConnection();

            System.out.println("Database Connected Successfully!");

            int choice;

            do {
                System.out.println("\n===== WELCOME TO VOYAGE VERSE =====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Browse States");
                System.out.println("4. Browse Cities");
                System.out.println("5. Browse Destinations");
                System.out.println("6. Manage Trip Bucket");
                System.out.println("7. Create Trip Plan");
                System.out.println("8. Browse Hotels");
                System.out.println("9. Book Hotel");
                System.out.println("10. Logout");
                System.out.println("0. Exit");

                System.out.print("\nEnter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    UserRegistration.register(conn, sc);
                }

                else if (choice == 2) {
                    loggedIn = UserLogin.login(conn, sc);
                }

                else if (choice == 10) {
                    if (loggedIn) {
                        UserLogout.logout();
                        loggedIn = false;
                    } else {
                        System.out.println("Please login first!");
                    }
                }

              else if (choice == 3) {
    BrowseStates.displayStates(conn);
}
                 else if (choice == 4) {
                 BrowseCities.displayCities(conn);
                  }
                  else if (choice == 5) {
                 DestinationDetails.displayDetails(conn);
                 }
                 else if (choice == 7) {
                 if (loggedIn) {
                 TripPlan.createPlan(conn, sc, UserLogin.userId);
                } else {
                System.out.println("Please login first!");
                }
            }
            else if (choice == 6) {
                 if (loggedIn) {
               AddToTripBucket.addDestination(conn, sc, UserLogin.userId);
               } else {
                 System.out.println("Please login first!");
                }
                }
                else if (choice == 8) {
                BrowseHotels.displayHotels(conn);
                }
                else if (choice == 9) {
                if (loggedIn) {
                  HotelBooking.bookHotel(conn, sc, UserLogin.userId);
               } else {
                 System.out.println("Please login first!");
              }
             }
                else if (choice == 0) {
                    System.out.println("Thank you for using Voyage Verse!");
                }

                else {
                    System.out.println("Feature coming soon!");
                }

            } while (choice != 0);

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
