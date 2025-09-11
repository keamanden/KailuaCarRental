//Handles all the scripts for the Database Connection

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

        private static final String URL = "jdbc:mysql://localhost:3306/KailueCarRental";
        private static final String USER = "root";
        private static final String PASSWORD = "keamanden";

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }


}
