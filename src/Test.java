import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.stream;

public class Test {

    static DataBaseManager dbManager = new DataBaseManager();


    public static void main(String[] args) throws SQLException {

        Connection conn = new DataBaseManager().getConnection();


        List<Car> cars = Car.allCarsToList(conn);

        cars.stream().sorted(Comparator.comparing(Car::getMileage)).forEach(System.out::println);

        for (Car car : cars ){

            System.out.println(car);

            System.out.println("-------------------");


        }

        //viewCars(new Scanner(System.in));
    }

    public static void viewCars(Scanner sc) throws SQLException {
        Connection conn = dbManager.getConnection();
        String sql = "SELECT * FROM Cars WHERE carID";
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        }

        System.out.println("1. Edit Car");
        System.out.println("2. Back to Main Menu");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.print("Enter carID to edit: ");
                int carID = sc.nextInt();
                Car car = retrieveCar(carID);
                if (car != null) {
                    editCar(car);
                    updateCar(car);
                } else {
                    System.out.println("Car not found.");
                }
            }
            case 2 -> System.out.println("Returning to Main Menu...");
            default -> System.out.println("Invalid choice.");
        }
    }

    public static Car retrieveCar(int carID) throws SQLException {

        Connection conn = dbManager.getConnection();
        String sql = "SELECT * FROM Cars";
        try (conn; PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            Car car = null;
            if (rs.next()) {
                // Replace with actual Car constructor and fields
                car = new Car(
                        rs.getInt("carID"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        rs.getString("FuelType"),
                        rs.getString("Registration"),
                        rs.getDate("firstRegistration").toLocalDate(),
                        rs.getInt("mileage")
                );

            }
            return car;
        }
    }

    public static void editCar(Car car) throws SQLException {

        retrieveCar(car.getCarID());


        System.out.println("1. Brand: " + car.getBrand());
        System.out.println("2. Model: " + car.getModel());
        System.out.println("3. Fuel Type: " + car.getFuelType());
        System.out.println("4. Registration: " + car.getRegistration());
        System.out.println("5. First Registration: " + car.getFirstRegistration());
        System.out.println("6. Mileage: " + car.getMileage());
        System.out.print("Select field to update (1-6): ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice){
            case 1 -> {
                System.out.print("Enter new Brand: ");
                String newBrand = scanner.next();
                car.setBrand(newBrand);
            }
            case 2 -> {
                System.out.print("Enter new Model: ");
                String newModel = scanner.next();
                car.setModel(newModel);
            }
            case 3 -> {
                System.out.print("Enter new Fuel Type: ");
                String newFuelType = scanner.next();
                car.setFuelType(newFuelType);
            }
            case 4 -> {
                System.out.print("Enter new Registration: ");
                String newRegistration = scanner.next();
                car.setRegistration(newRegistration);
            }
            case 5 -> {
                System.out.print("Enter new First Registration (YYYY-MM-DD): ");
                String newFirstReg = scanner.next();
                car.setFirstRegistration(java.time.LocalDate.parse(newFirstReg));
            }
            case 6 -> {
                System.out.print("Enter new Mileage: ");
                int newMileage = scanner.nextInt();
                car.setMileage(newMileage);
            }
            default -> System.out.println("Invalid choice.");
        }


    }

    public static void updateCar(Car car) {

        String sql = "UPDATE Cars SET Brand = ?, Model = ?, FuelType = ?, Registration = ?, firstRegistration = ?, mileage = ? WHERE carID = ?";
        try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, car.getBrand());
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getFuelType());
            pstmt.setString(4, car.getRegistration());
            pstmt.setDate(5, java.sql.Date.valueOf(car.getFirstRegistration()));
            pstmt.setInt(6, car.getMileage());
            pstmt.setInt(7, car.getCarID());
            pstmt.executeUpdate();
            System.out.println("Car updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating car: " + e.getMessage());
        }



    }

}