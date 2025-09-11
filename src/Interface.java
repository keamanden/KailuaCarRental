import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Interface {

    public void Menu() {

        DataBaseManager dbManager = new DataBaseManager();

        try (
                Connection conn = dbManager.getConnection();
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Choose an option:");
            System.out.println("1. Create Customer");
            System.out.println("2. Create Car");
            System.out.println("3. Create Lease");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createCustomer(conn, scanner);
                case 2 -> createCar(conn, scanner);
                case 3 -> Lease.createLease(conn, scanner);
                default -> System.out.println("Invalid choice.");
            }

        } catch (
                SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}









