package models;

import interfaces.IPassword;

public class Password implements IPassword {
    private final int ownerId;
    private final int passwordId;
    private final String url;
    private final String login;
    private final String encryptedPassword;

    public Password(int ownerId, int passwordId, String url, String login, String encryptedPassword) {
        this.ownerId = ownerId;
        this.passwordId = passwordId;
        this.url = url;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
    }
    public Password(int ownerId, String url, String login, String encryptedPassword) {
        this.ownerId = ownerId;
        this.passwordId = -1;
        this.url = url;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public int getOwnerId() {
        return ownerId;
    }

    @Override
    public int getPasswordId() {
        return passwordId;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}
