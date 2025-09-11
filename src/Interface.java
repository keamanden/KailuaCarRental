import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Interface {



    DataBaseManager dbManager = new DataBaseManager();

    public Interface() throws SQLException {
    }


    public void Menu(){

    try ( Connection conn = dbManager.getConnection();
    Scanner scanner = new Scanner(System.in)) {

        System.out.println("Welcome to the Lease Management Console");
        System.out.println("Choose an option:");
        System.out.println("1. Create Customer");
        System.out.println("2. Create Car");
        System.out.println("3. Create Lease");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> createCustomer(conn, scanner);
            case 2 -> createCar(conn, scanner);
            case 3 -> createLease(conn, scanner);
            default -> System.out.println("Invalid choice.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}




}



