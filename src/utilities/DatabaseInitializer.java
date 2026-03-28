package utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class DatabaseInitializer {
    private static final String CREATE_USERS_TABLE_SQL = """
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            login TEXT NOT NULL UNIQUE,
            passwordHash TEXT NOT NULL,
            salt TEXT NOT NULL
        );
        """;
    public static void init() {
        createUsersTable();
    }
    private static void createUsersTable() {
        try (Connection con = DatabaseConnection.connectDb(); Statement stmt = con.createStatement()) {
            stmt.execute(CREATE_USERS_TABLE_SQL);
        } catch (SQLException e) {
            System.err.println("Failed to create users table (ref: janager.src.utilities.DatabaseInitializer): " + e.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "Failed to create users table (ref: janager.src.utilities.DatabaseInitializer): " + e.getMessage(), "Database critical error!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
