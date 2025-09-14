import com.mysql.cj.protocol.Resultset;

import java.nio.channels.ScatteringByteChannel;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Lease {

    private Customer customer;
    private Car car;
    private int leaseID;
    private int customerID;
    private int carID;
    private String leaseAddress;
    private int leaseZip;
    private String actualDriverLicenseNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int milesBought;
    private int currentMileage;

    public Lease(Customer customer, Car car, int leaseID, int customerID, String leaseAddress, int carID, int leaseZip, String actualDriverLicenseNumber, LocalDateTime startDate, LocalDateTime endDate, int milesBought, int currentMileage) throws SQLException {
        this.customer = customer;
        this.car = car;
        this.leaseID = leaseID;
        this.customerID = customerID;
        this.leaseAddress = leaseAddress;
        this.carID = carID;
        this.leaseZip = leaseZip;
        this.actualDriverLicenseNumber = actualDriverLicenseNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.milesBought = milesBought;
        this.currentMileage = currentMileage;
    }


    public static Lease createLease(Scanner scanner) throws SQLException {


        try {
            Connection conn = DataBaseManager.getConnection();
            System.out.print("Select a customer");
            Customer.displayCustomers();
            scanner.hasNextLine();
            int choice = scanner.nextInt();
            String sql = "SELECT * FROM Customer WHERE customerID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, choice);
            ResultSet rs = pstmt.executeQuery();
            int customerID = 0;
            if (rs.next()) {
                customerID = rs.getInt("customerID");

            }
            scanner.nextLine();
            System.out.print("Select a car");
            Car.displayCars(conn);
            int carID = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Lease Address: ");
            String address = scanner.nextLine();
            System.out.print("Lease Zip: ");
            int zip = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Actual Driver License Number: ");
            String license = scanner.nextLine();
            System.out.print("Start Date (YYYY-MM-DDTHH:MM): ");
            LocalDateTime start = LocalDateTime.parse(scanner.nextLine());
            System.out.print("End Date (YYYY-MM-DDTHH:MM): ");
            LocalDateTime end = LocalDateTime.parse(scanner.nextLine());
            System.out.print("Miles Bought: ");
            int milesBought = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Current Mileage: ");
            int currentMileage = scanner.nextInt();
            scanner.nextLine();

            return new Lease(
                    null,
                    null,
                    0,
                    customerID,
                    address,
                    carID,
                    zip,
                    license,
                    start,
                    end,
                    milesBought,
                    currentMileage
            );


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveToDatabase(Connection conn) throws SQLException {
        String sql = "INSERT INTO Lease ( customerID, carID, leaseAddress, leaseZip, actualDriverLicenseNumber, startDate, endDate, milesBought, currentMileage) VALUES ( null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(2, customerID);
            pstmt.setInt(3, carID);
            pstmt.setString(4, leaseAddress);
            pstmt.setInt(5, leaseZip);
            pstmt.setString(6, actualDriverLicenseNumber);
            pstmt.setTimestamp(7, Timestamp.valueOf(startDate));
            pstmt.setTimestamp(8, Timestamp.valueOf(endDate));
            pstmt.setInt(9, milesBought);
            pstmt.setInt(10, currentMileage);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving lease to database: " + e.getMessage());
            throw e;
        }

    }


    @Override
    public String toString() {
        return "Lease{" +
                "customer=" + customer +
                ", car=" + car +
                ", leaseID=" + leaseID +
                ", customerID=" + customerID +
                ", carID=" + carID +
                ", leaseAddress='" + leaseAddress + '\'' +
                ", leaseZip=" + leaseZip +
                ", actualDriverLicenseNumber='" + actualDriverLicenseNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", milesBought=" + milesBought +
                ", currentMileage=" + currentMileage +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getLeaseAddress() {
        return leaseAddress;
    }

    public void setLeaseAddress(String leaseAddress) {
        this.leaseAddress = leaseAddress;
    }

    public int getLeaseZip() {
        return leaseZip;
    }

    public void setLeaseZip(int leaseZip) {
        this.leaseZip = leaseZip;
    }

    public String getActualDriverLicenseNumber() {
        return actualDriverLicenseNumber;
    }

    public void setActualDriverLicenseNumber(String actualDriverLicenseNumber) {
        this.actualDriverLicenseNumber = actualDriverLicenseNumber;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getMilesBought() {
        return milesBought;
    }

    public void setMilesBought(int milesBought) {
        this.milesBought = milesBought;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

}