import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class Main {




    public static void main(String[] args) throws SQLException {


        Connection conn = DataBaseManager.getConnection(); // Open connection

        Interface anInterface = new Interface(conn); // Pass connection to Interface
        anInterface.Menu();

        conn.close();



    }
}
