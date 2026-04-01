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
    private static final String CREATE_CREDENTIALS_TABLE_SQL = """
        CREATE TABLE IF NOT EXISTS credentials (
            credentialId INTEGER PRIMARY KEY AUTOINCREMENT,
            ownerId INTEGER NOT NULL,
            url TEXT NOT NULL,
            login TEXT NOT NULL,
            encryptedPassword TEXT NOT NULL
        );
        """;
    public static void init() {
        createTable("users", CREATE_USERS_TABLE_SQL);
        createTable("credentials", CREATE_CREDENTIALS_TABLE_SQL);
    }
    private static void createTable(String tableName, String SQL_STATEMENT) {
        try (Connection con = DatabaseConnection.connectDb(); Statement stmt = con.createStatement()) {
            stmt.execute(SQL_STATEMENT);
        } catch (SQLException e) {
            System.err.println("Failed to create the `" + tableName + "` table (ref: janager.src.utilities.DatabaseInitializer.createTable): " + e.getMessage());
            DialogUtils.showErrorDialog("Database critical error!", "Failed to create the `" + tableName + "` table (ref: janager.src.utilities.DatabaseInitializer.createTable): " + e.getMessage());
            System.exit(1);
        }
    }
}
