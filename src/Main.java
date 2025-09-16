import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class Main {




    public static void main(String[] args) throws SQLException {


        Connection conn = DataBaseManager.getConnection();

        Interface anInterface = new Interface(conn);
        anInterface.Menu();
        conn.close();



    }
}
