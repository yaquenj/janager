package interfaces;

public interface IUser {
    int getId();
    String getLogin();
    String getPasswordHash();
    String getSalt();
}
