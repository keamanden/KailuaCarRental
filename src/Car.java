import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
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

