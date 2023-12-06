package com.ahmad.web3V.Utils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.stereotype.Component;


@Component
public class RSAKeyProperties {

    // RSA public key
    private RSAPublicKey publicKey;

    // RSA private key
    private RSAPrivateKey privateKey;

    // Constructor to generate RSA key pair and initialize properties
    public RSAKeyProperties() {
        // Generate an RSA key pair using the KeyGeneratorUtility
        KeyPair pair = KeyGeneratorUtility.generateRsaKey();

        // Set the public and private keys
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
    }

    // Getter for the public key
    public RSAPublicKey getPublicKey() {
        return this.publicKey;
    }

    // Setter for the public key
    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    // Getter for the private key
    public RSAPrivateKey getPrivateKey() {
        return this.privateKey;
    }

    // Setter for the private key
    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
