import frames.AuthFrame;
import models.User;
import utilities.DatabaseInitializer;
import utilities.DbUserUtils;

public class Main {
    public static void main(String[] args) {

        //? First database initialization - does not overwrite it but creates a new one with necessary tables, if it doesn't exist
        DatabaseInitializer.init();
        DbUserUtils.createUser(new User("yaquenj", "hasełko masełko", "sól"));
        var usr = DbUserUtils.getUser("yaqeuenj");
        new AuthFrame();

    }
}
