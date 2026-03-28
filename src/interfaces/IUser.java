package interfaces;

public interface IUser {
    boolean createUser();
    boolean editUser();
    String getLogin();
    boolean checkPassword();
    void setPassword();
}
