package models;

import interfaces.ICredential;

public class Credential implements ICredential {
    private final int ownerId;
    private final int credentialId;
    private final String url;
    private final String login;
    private final String encryptedPassword;

    public Credential(int ownerId, int passwordId, String url, String login, String encryptedPassword) {
        this.ownerId = ownerId;
        this.credentialId = passwordId;
        this.url = url;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
    }
    public Credential(int ownerId, String url, String login, String encryptedPassword) {
        this.ownerId = ownerId;
        this.credentialId = -1;
        this.url = url;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public int getOwnerId() {
        return ownerId;
    }

    @Override
    public int getCredentialId() {
        return credentialId;
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
