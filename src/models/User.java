package models;

import interfaces.IUser;

public class User implements IUser {
    private final int id;
    private String login;
    private String passwordHash;
    private String salt;

    public User(int id, String login, String passwordHash, String salt) {
        this.id = id;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
