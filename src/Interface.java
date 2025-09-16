import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Interface {


    public Interface(Connection conn) {
    }

    public void Menu() throws SQLException {



        while (true) {
            DataBaseManager dbManager = new DataBaseManager();
            Connection conn = dbManager.getConnection();
            Scanner scanner = new Scanner(System.in);
            {

                System.out.println("Choose an option:");
                System.out.println("1. Create Customer");
                System.out.println("2. Create Car");
                System.out.println("3. Create Lease");
                System.out.println("4. Display Leases for Customer");
                System.out.println("5. View Cars");
                System.out.println("99. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1 -> {
                        Customer customer = Customer.createCustomer(scanner);
                        customer.saveToDatabase(conn);
                    }
                    case 2 -> {

                        Car car = Car.createCar(scanner);
                        car.saveToDatabase(conn);
                    }
                    case 3 -> {

                        Lease lease = Lease.createLease(scanner);
                        lease.saveToDatabase(conn);
                    }

                    case 4 -> {
                        Lease.displayLease(conn, scanner);
                    }

                    case 5 -> {

                        Car.viewCars(new Scanner(System.in));
                    }
                    case 99 -> {
                        System.out.println("Exiting...");
                        conn.close();
                        scanner.close();
                        return;

                    }

                    default -> System.out.println("Invalid choice.");
                }


            }
        }
    }
}







