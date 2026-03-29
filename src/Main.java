import frames.MainFrame;
import utilities.DatabaseInitializer;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {

        // First database initialization - does not overwrite it but creates a new one with necessary tables, if it doesn't exist
        DatabaseInitializer.init();
        new MainFrame();

    }
}
