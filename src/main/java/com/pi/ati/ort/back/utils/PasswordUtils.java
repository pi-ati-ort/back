package com.pi.ati.ort.back.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int SALT_LENGTH = 16; // you can adjust this value

    public static String generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
