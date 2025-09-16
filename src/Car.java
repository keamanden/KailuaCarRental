import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Car {
    private int carID;
    private String brand;
    private String model;
    private String fuelType;
    private String registration;
    private LocalDate firstRegistration;
    private int mileage;

    public Car(int carID, String brand, String model, String fuelType, String registration, LocalDate firstRegistration, int mileage) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.registration = registration;
        this.firstRegistration = firstRegistration;
        this.mileage = mileage;
    }



    public static Car createCar(Scanner scanner) {
        try (scanner) {

            System.out.print("Brand: ");
            String brand = scanner.nextLine();

            System.out.print("Model: ");
            String model = scanner.nextLine();

            System.out.print("Fuel Type: ");
            String fuelType = scanner.nextLine();

            System.out.print("Registration: ");
            String registration = scanner.nextLine();

            System.out.print("First Registration Date (YYYY-MM-DD): ");
            LocalDate firstRegistration = LocalDate.parse(scanner.nextLine());

            System.out.print("Mileage: ");
            int mileage = Integer.parseInt(scanner.nextLine());

            return new Car(0, brand, model, fuelType, registration, firstRegistration, mileage);

        } catch (Exception e) {
            System.out.println("Error creating car: " + e.getMessage());
            return null;
        }
    }


    public void saveToDatabase(Connection conn) {
        String sql = "INSERT INTO Cars (Brand, Model, FuelType, Registration, FirstRegistration, Mileage) " +
                "VALUES ( ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, this.brand);
            stmt.setString(2, this.model);
            stmt.setString(3, this.fuelType);
            stmt.setString(4, this.registration);
            stmt.setDate(5, Date.valueOf(this.firstRegistration));
            stmt.setInt(6, this.mileage);
            stmt.executeUpdate();
            System.out.println("Car saved to database successfully.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }


    public static void displayCars(Connection conn) {


        String sql = "SELECT * FROM Cars";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             var rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int carID = rs.getInt("CarID");
                String brand = rs.getString("Brand");
                String model = rs.getString("Model");
                String fuelType = rs.getString("FuelType");
                String registration = rs.getString("Registration");
                LocalDate firstRegistration = rs.getDate("FirstRegistration").toLocalDate();
                int mileage = rs.getInt("Mileage");

                Car car = new Car(carID, brand, model, fuelType, registration, firstRegistration, mileage);
                System.out.println(car);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving cars: " + e.getMessage());
        }

    }

    public static void viewCars(Scanner sc) throws SQLException {



        Connection conn = DataBaseManager.getConnection();
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

        Connection conn = DataBaseManager.getConnection();
        String sql = "SELECT * FROM Cars WHERE carID = ?";


        try (conn; PreparedStatement stmt = conn.prepareStatement(sql); ) {
            stmt.setInt(1, carID);
            ResultSet rs = stmt.executeQuery();
            Car car = null;
            if (rs.next()) {

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

    public static void updateCar(Car car) throws SQLException {

        DataBaseManager dbManager = new DataBaseManager();

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

        switch (choice) {
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

    public static List<Car> allCarsToList(Connection conn) throws SQLException {


        String sql = "SELECT * FROM Cars";

        try (conn; PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("carID"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        rs.getString("FuelType"),
                        rs.getString("Registration"),
                        rs.getDate("firstRegistration").toLocalDate(),
                        rs.getInt("mileage")
                );
                cars.add(car);
            }
            return cars;
        }




    }




        @Override
    public String toString() {
        return "Car{" +
                "carID=" + carID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", registration='" + registration + '\'' +
                ", firstRegistration=" + firstRegistration +
                ", mileage=" + mileage +
                '}';
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public LocalDate getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(LocalDate firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}

