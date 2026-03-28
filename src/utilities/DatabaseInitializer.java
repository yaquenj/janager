package utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
            DialogUtils.showErrorDialog("Database critical error!", "Failed to create users table (ref: janager.src.utilities.DatabaseInitializer.createUsersTable): " + e.getMessage());
            System.exit(1);
        }
    }
}
