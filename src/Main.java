import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class Main {




    public static void main(String[] args) throws SQLException {

        DataBaseManager dbManager = new DataBaseManager();
        Connection conn = dbManager.getConnection(); // Open connection

        Interface anInterface = new Interface(conn); // Pass connection to Interface
        anInterface.Menu();

        conn.close();



    }
}
