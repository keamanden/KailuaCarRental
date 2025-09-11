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

    public Car(int carID, String brand, String model, String fuelType, String registration, LocalDate firstRegistration, int mileage) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.registration = registration;
        this.firstRegistration = firstRegistration;
        this.mileage = mileage;
    }

    private String fuelType;
    private String registration;
    private LocalDate firstRegistration;
    private int mileage;


    public static void createCar(Connection conn, Scanner scanner) {
        try (scanner) {
            System.out.print("Car ID: ");
            int carID = Integer.parseInt(scanner.nextLine());

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

            String sql = "INSERT INTO Cars (CarID, Brand, Model, FuelType, Registration, FirstRegistration, Mileage) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, carID);
                stmt.setString(2, brand);
                stmt.setString(3, model);
                stmt.setString(4, fuelType);
                stmt.setString(5, registration);
                stmt.setDate(6, Date.valueOf(firstRegistration));
                stmt.setInt(7, mileage);
                stmt.executeUpdate();
                System.out.println("Car created successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Input error: " + e.getMessage());
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

