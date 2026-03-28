package utilities;

import models.User;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class AuthUtils {
    public static void registerUser(String username, char[] password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        var userSalt = generateSalt();
        var user = new User(username, hashPassword(password, userSalt), Arrays.toString(userSalt));
        DbUserUtils.createUser(user);
    }
    public static boolean loginUser(String username, char[] password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        var user = DbUserUtils.getUser(username);
        return checkPassword(user, password);
    }
    public static byte[] generateSalt() {
        var sr = new SecureRandom();
        var salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
    public static String hashPassword(char[] password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        var spec = new PBEKeySpec(password, salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
    }
    public static boolean checkPassword(User user, char[] password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String computedHash = hashPassword(password, user.getSalt().getBytes());
        return computedHash.equals(user.getPasswordHash());
    }
}
