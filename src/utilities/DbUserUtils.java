package utilities;

import java.sql.*;
import java.util.Objects;

import models.User;

public class DbUserUtils {
    private static final String GET_USER_BY_ID_SQL = """
        SELECT * FROM users WHERE id = ?;
        """;
    private static final String GET_USER_BY_LOGIN_SQL = """
        SELECT * FROM users WHERE login = ?;
        """;
    private static final String CREATE_USER_SQL = """
        INSERT OR IGNORE INTO users (login, passwordHash, salt) VALUES (?, ?, ?);
        """;
    public static User getUser(int userId) {
        try (var con = DatabaseConnection.connectDb(); var stmt = con.prepareStatement(GET_USER_BY_ID_SQL);) {
            stmt.setInt(1, userId);
            var result = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Couldn't GET query the database! (ref: janager.src.utilities.DbUserUtils.getUser [by id]): " + e.getMessage());
            DialogUtils.showErrorDialog("Database GET query failed!", "Couldn't GET query the database! (ref: janager.src.utilities.DbUserUtils.getUser [by id]): " + e.getMessage());
        }
        return null;
    }
    public static User getUser(String login) {
        try (var con = DatabaseConnection.connectDb(); var stmt = con.prepareStatement(GET_USER_BY_LOGIN_SQL);) {
            stmt.setString(1, login);
            try (var result = stmt.executeQuery()) {
                if (result.next()) {
                    return new User(
                        result.getInt("id"),
                        result.getString("login"),
                        result.getString("passwordHash"),
                        result.getString("salt")
                    );
                }
            } catch (SQLException e) {
                System.err.println("Couldn't GET query the database! ... " + e.getMessage());
            };
        } catch (SQLException e) {
            System.err.println("Couldn't GET query the database! (ref: janager.src.utilities.DbUserUtils.getUser [by login]): " + e.getMessage());
            DialogUtils.showErrorDialog("Database GET query failed!", "Couldn't GET query the database! (ref: janager.src.utilities.DbUserUtils.getUser [by login]): " + e.getMessage());
        }
        return null;
    }
    public static void createUser(User user) {
        if (Objects.equals(Objects.requireNonNull(getUser(user.getLogin())).getLogin(), user.getLogin())) {
            DialogUtils.showInfoDialog("User creation failed!", "User '" + user.getLogin() + "' already exists! Please choose another login.");
            return;
        }
        try (var con = DatabaseConnection.connectDb(); var stmt = con.prepareStatement(CREATE_USER_SQL)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getSalt());
            var result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Couldn't SET query the database! (ref: janager.src.utilities.DbUserUtils.createUser): " + e.getMessage());
            DialogUtils.showErrorDialog("Database SET query failed!", "Couldn't SET query the database! (ref: janager.src.utilities.DbUserUtils.createUser): " + e.getMessage());
        }
    }
    public static void updateUser(User oldUser, User newUser) {

    }
    public static void deleteUser(int userId) {

    }
    public static void deleteUser(String login) {

    }
}
