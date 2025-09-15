import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Lease {

    private Customer customer;
    private Car car;
    private int leaseID;
    private int customerID;
    private int carID;
    private String Address;
    private int Zip;
    private String actualDriverLicenseNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int milesBought;
    private int currentMileage;

    public Lease(Customer customer, Car car, int leaseID, int customerID, String Address, int carID, int leaseZip, String actualDriverLicenseNumber, LocalDateTime startDate, LocalDateTime endDate, int milesBought, int currentMileage) throws SQLException {
        this.customer = customer;
        this.car = car;
        this.leaseID = leaseID;
        this.customerID = customerID;
        this.Address = Address;
        this.carID = carID;
        this.Zip = leaseZip;
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

    public static void displayLease(Connection conn, Scanner scanner) {

        String sql = "SELECT * FROM Lease " +
                "JOIN Customer ON Lease.CustomerID = Customer.CustomerID " +
                "JOIN Cars ON Lease.CarID = Cars.CarID " +
                "WHERE Lease.CustomerID = ?";
        try {
            System.out.print("Enter Customer ID to view leases: ");
            int customerID = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String leaseInfo = rs.getInt("LeaseID") + ", " +
                        rs.getInt("CustomerID") + ", " +
                        rs.getInt("CarID") + ", " +
                        rs.getString("Address") + ", " +
                        rs.getInt("Zip") + ", " +
                        rs.getString("ActualDriverLicenseNumber") + ", " +
                        rs.getTimestamp("StartDate").toLocalDateTime() + ", " +
                        rs.getTimestamp("EndDate").toLocalDateTime() + ", " +
                        rs.getInt("MilesBought") + ", " +
                        rs.getInt("CurrentMileage");

                String customerInfo = rs.getString("Name") + ", " +
                        rs.getString("Address") + ", " +
                        rs.getInt("Zip") + ", " +
                        rs.getString("City") + ", " +
                        rs.getString("MobilePhone") + ", " +
                        rs.getString("Email") + ", " +
                        rs.getString("DriverLicenseNumber") + ", " +
                        rs.getDate("DriverSinceDate");

                String carInfo = rs.getString("Brand") + ", " +
                        rs.getString("Model") + ", " +
                        rs.getString("FuelType") + ", " +
                        rs.getString("Registration") + ", " +
                        rs.getDate("FirstRegistration") + ", " +
                        rs.getInt("Mileage");

                System.out.println("Lease Info: " + leaseInfo);
                System.out.println("Customer Info: " + customerInfo);
                System.out.println("Car Info: " + carInfo);
                System.out.println("---------------------------");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveToDatabase(Connection conn) throws SQLException {
        String sql = "INSERT INTO Lease ( customerID, carID, Address, Zip, actualDriverLicenseNumber, startDate, endDate, milesBought, currentMileage) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerID);
            pstmt.setInt(2, carID);
            pstmt.setString(3, Address);
            pstmt.setInt(4, Zip);
            pstmt.setString(5, actualDriverLicenseNumber);
            pstmt.setTimestamp(6, Timestamp.valueOf(startDate));
            pstmt.setTimestamp(7, Timestamp.valueOf(endDate));
            pstmt.setInt(8, milesBought);
            pstmt.setInt(9, currentMileage);
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
                ", leaseAddress='" + Address + '\'' +
                ", leaseZip=" + Zip +
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public int getZip() {
        return Zip;
    }

    public void setZip(int zip) {
        this.Zip = zip;
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