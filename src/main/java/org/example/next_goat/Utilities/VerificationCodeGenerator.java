package org.example.next_goat.Utilities;

import java.util.Random;

public class VerificationCodeGenerator {

    public static String generateCode() {
        Random random = new Random();
        int code = 1000 + random.nextInt(9000); // Generar número entre 1000 y 9999
        return String.valueOf(code);
    }
}
