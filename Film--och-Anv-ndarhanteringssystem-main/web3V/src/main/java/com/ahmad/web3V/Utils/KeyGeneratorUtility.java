package com.ahmad.web3V.Utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;


public class KeyGeneratorUtility {

    // Method to generate an RSA key pair
    public static KeyPair generateRsaKey() {
        KeyPair keyPair;

        try {
            // Create a KeyPairGenerator instance for RSA
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            // Initialize the KeyPairGenerator with a key size of 2048 bits
            keyPairGenerator.initialize(2048);

            // Generate an RSA key pair
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            // Throw an IllegalStateException in case of an exception during key pair generation
            throw new IllegalStateException("Error generating RSA key pair", e);
        }

        return keyPair;
    }
}
