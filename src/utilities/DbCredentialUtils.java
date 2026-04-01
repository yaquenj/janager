package utilities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Credential;

public class DbCredentialUtils {
    private static final String GET_CREDENTIAL_BY_ID_SQL = """
        SELECT * FROM credentials WHERE credentialId = ?;
        """;
    private static final String GET_USER_CREDENTIALS_BY_USER_ID_SQL = """
        SELECT * FROM credentials WHERE ownerId = ? ORDER BY url;
        """;
    private static final String CREATE_CREDENTIAL_SQL = """
        INSERT OR IGNORE INTO credentials (ownerId, url, login, encryptedPassword) VALUES (?, ?, ?, ?);
        """;
    private static final String UPDATE_CREDENTIAL_SQL = """
        UPDATE credentials SET ownerId = ?, url = ?, login = ?, encryptedPassword = ? WHERE credentialId = ?;
        """;
    private static final String DELETE_CREDENTIAL_SQL = """
        DELETE FROM credentials WHERE credentialId = ?;
        """;
    public static Credential getCredential(int credentialId) {
        try (var con = DatabaseConnection.connectDb();
             var stmt = con.prepareStatement(GET_CREDENTIAL_BY_ID_SQL)) {
            stmt.setInt(1, credentialId);

            try (var result = stmt.executeQuery()) {
                if (result.next()) {
                    return new Credential(
                            result.getInt("credentialId"),
                            result.getInt("ownerId"),
                            result.getString("url"),
                            result.getString("login"),
                            result.getString("encryptedPassword")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Couldn't GET query the database! (ref: janager.src.utilities.DbCredentialUtils.getCredential): " + e.getMessage());
            DialogUtils.showErrorDialog(
                "Database GET query failed!",
                "Couldn't GET query the database! (ref: janager.src.utilities.DbCredentialUtils.getCredential): " + e.getMessage()
            );
        }
        return null;
    }
    public static ArrayList<Credential> getUserCredentialsById(int userId) {
        var credentialsList = new java.util.ArrayList<Credential>();
        try (var con = DatabaseConnection.connectDb(); var stmt = con.prepareStatement(GET_USER_CREDENTIALS_BY_USER_ID_SQL)) {
            stmt.setInt(1, userId);
            try (var result = stmt.executeQuery()) {
                while (result.next()) {
                    credentialsList.add(new Credential(result.getInt("credentialId"), result.getInt("ownerId"), result.getString("url"), result.getString("login"), result.getString("encryptedPassword")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Couldn't GET query the database! (ref: janager.src.utilities.DbCredentialUtils.getUserCredentialsById): " + e.getMessage());
            DialogUtils.showErrorDialog(
                "Database GET query failed!",
                "Couldn't GET query the database! (ref: janager.src.utilities.DbCredentialUtils.getUserCredentialsById): " + e.getMessage()
            );
        }
        return credentialsList;
    }
    public static void createCredential(Credential credential) {
        try (var con = DatabaseConnection.connectDb(); var stmt = con.prepareStatement(CREATE_CREDENTIAL_SQL)) {
            stmt.setInt(1, credential.getOwnerId());
            stmt.setString(2, credential.getUrl());
            stmt.setString(3, credential.getLogin());
            stmt.setString(4, credential.getEncryptedPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Couldn't SET query the database! (ref: janager.src.utilities.DbCredentialUtils.createCredential): " + e.getMessage());
            DialogUtils.showErrorDialog(
                "Database SET query failed!",
                "Couldn't SET query the database! (ref: janager.src.utilities.DbCredentialUtils.createCredential): " + e.getMessage()
            );
        }
    }
    public static void updateCredential(int oldCredentialId, Credential newCredential) {
            try (var con = DatabaseConnection.connectDb(); var stmt = con.prepareStatement(UPDATE_CREDENTIAL_SQL)) {
            stmt.setInt(1, newCredential.getOwnerId());
            stmt.setString(2, newCredential.getUrl());
            stmt.setString(3, newCredential.getLogin());
            stmt.setString(4, newCredential.getEncryptedPassword());
            stmt.setInt(5, oldCredentialId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Couldn't UPDATE query the database! (ref: janager.src.utilities.DbCredentialUtils.updateCredential): " + e.getMessage());
            DialogUtils.showErrorDialog(
                "Database UPDATE query failed!",
                "Couldn't UPDATE query the database! (ref: janager.src.utilities.DbCredentialUtils.updateCredential): " + e.getMessage()
            );
        }
    }
    public static void deleteCredential(int credentialId) {
        try (var con = DatabaseConnection.connectDb(); var stmt = con.prepareStatement(DELETE_CREDENTIAL_SQL)) {
            stmt.setInt(1, credentialId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Couldn't DELETE query the database! (ref: janager.src.utilities.DbCredentialUtils.deleteCredential): " + e.getMessage());
            DialogUtils.showErrorDialog(
                "Database DELETE query failed!",
                "Couldn't DELETE query the database! (ref: janager.src.utilities.DbCredentialUtils.deleteCredential): " + e.getMessage()
            );
        }
    }
}
