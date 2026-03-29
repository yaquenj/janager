package models;

import interfaces.IUser;

public class User implements IUser {
    private final int id;
    private final String login;
    private final String passwordHash;
    private final String salt;

    public User(int id, String login, String passwordHash, String salt) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }
    public User(String login, String passwordHash, String salt) {
        this.id = -1;
        this.login = login;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String getSalt() {
        return salt;
    }

}
