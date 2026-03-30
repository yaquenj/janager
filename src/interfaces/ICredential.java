package interfaces;

public interface ICredential {
    int getOwnerId();
    int getCredentialId();
    String getUrl();
    String getLogin();
    String getEncryptedPassword();
}
