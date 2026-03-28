package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:janager.db";
    public static Connection connectDb() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
