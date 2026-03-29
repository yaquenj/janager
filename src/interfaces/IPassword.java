package interfaces;

public interface IPassword {
    int getOwnerId();
    int getPasswordId();
    String getUrl();
    String getLogin();
    String getEncryptedPassword();
}
