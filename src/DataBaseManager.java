//Handles all the scripts for the Database Connection

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";


    Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/Kailua_Car_Rental", "root", "keamanden");


    public DataBaseManager() throws SQLException {
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
