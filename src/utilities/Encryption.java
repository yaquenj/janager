package utilities;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Encryption {
    private static final String KEY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";
    private static final int KEY_LENGTH = 256;
    private static final int PBKDF2_ITERATIONS = 65536;
    private static final int SALT_LENGTH = 16;
    private static final int IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 128;

    public char[] encryptPassword(char[] password, char[] masterPassword) {
        try {
            byte[] salt = new byte[SALT_LENGTH];
            byte[] iv = new byte[IV_LENGTH];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(salt);
            secureRandom.nextBytes(iv);

            SecretKey key = deriveKey(masterPassword, salt);

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH, iv));

            byte[] encrypted = cipher.doFinal(new String(password).getBytes(StandardCharsets.UTF_8));

            ByteBuffer buffer = ByteBuffer.allocate(salt.length + iv.length + encrypted.length);
            buffer.put(salt);
            buffer.put(iv);
            buffer.put(encrypted);

            return Base64.getEncoder().encodeToString(buffer.array()).toCharArray();
        } catch (Exception e) {
            throw new IllegalStateException("Password encryption failed", e);
        }
    }

    public char[] decryptPassword(char[] password, char[] masterPassword) {
        try {
            byte[] decoded = Base64.getDecoder().decode(new String(password));
            ByteBuffer buffer = ByteBuffer.wrap(decoded);

            byte[] salt = new byte[SALT_LENGTH];
            byte[] iv = new byte[IV_LENGTH];
            byte[] encrypted = new byte[buffer.remaining() - SALT_LENGTH - IV_LENGTH];

            buffer.get(salt);
            buffer.get(iv);
            buffer.get(encrypted);

            SecretKey key = deriveKey(masterPassword, salt);

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH, iv));

            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted, StandardCharsets.UTF_8).toCharArray();
        } catch (Exception e) {
            throw new IllegalStateException("Password decryption failed", e);
        }
    }

    private SecretKey deriveKey(char[] masterPassword, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(masterPassword, salt, PBKDF2_ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }
}
