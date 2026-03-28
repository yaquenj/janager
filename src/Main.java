import frames.AuthFrame;
import utilities.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {

        //? First database initialization - does not overwrite it but creates a new one with necessary tables, if it doesn't exist
        DatabaseInitializer.init();
        new AuthFrame();

    }
}
