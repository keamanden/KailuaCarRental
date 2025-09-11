import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Customer {
    private int customerID;
    private String name;
    private String address;
    private int zip;
    private String city;
    private String mobilePhone;
    private String email;
    private String driverLicenseNumber;
    private LocalDate driverSinceDate;


    public Customer(int customerID, String name, String address, int zip, String city, String mobilePhone, String email, String driverLicenseNumber, LocalDate driverSinceDate) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverSinceDate = driverSinceDate;
    }


    public static void createCustomer(Connection conn, Scanner scanner) {

        try (scanner) {
            System.out.print("Customer ID: ");
            int customerID = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Address: ");
            String address = scanner.nextLine();

            System.out.print("Zip Code: ");
            int zip = Integer.parseInt(scanner.nextLine());

            System.out.print("City: ");
            String city = scanner.nextLine();

            System.out.print("Mobile Phone: ");
            String mobilePhone = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Driver License Number: ");
            String licenseNumber = scanner.nextLine();

            System.out.print("Driver Since Date (YYYY-MM-DD): ");
            LocalDate driverSinceDate = LocalDate.parse(scanner.nextLine());

            String sql = "INSERT INTO Customer (CustomerID, Name, Address, Zip, City, MobilePhone, Email, DriverLicenseNumber, DriverSinceDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, customerID);
                stmt.setString(2, name);
                stmt.setString(3, address);
                stmt.setInt(4, zip);
                stmt.setString(5, city);
                stmt.setString(6, mobilePhone);
                stmt.setString(7, email);
                stmt.setString(8, licenseNumber);
                stmt.setDate(9, Date.valueOf(driverSinceDate));
                stmt.executeUpdate();
                System.out.println("Customer created successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Input error: " + e.getMessage());

        }

    }


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public LocalDate getDriverSinceDate() {
        return driverSinceDate;
    }

    public void setDriverSinceDate(LocalDate driverSinceDate) {
        this.driverSinceDate = driverSinceDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
                ", driverSinceDate=" + driverSinceDate +
                '}';
    }
}
