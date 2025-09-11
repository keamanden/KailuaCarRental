import java.time.LocalDateTime;

public class Lease {

    private Customer customer;
    private Car car;

    public Lease(Customer customer, Car car, int leaseID, int customerID, String leaseAddress, int carID, int leaseZip, String actualDriverLicenseNumber, LocalDateTime startDate, LocalDateTime endDate, int milesBought, int currentMileage) {
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