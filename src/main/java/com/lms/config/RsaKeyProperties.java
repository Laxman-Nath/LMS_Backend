
package com.lms.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {
    private Resource publicKey;
    private Resource privateKey;

    // Add direct string properties for raw key content (used in prod)
    private String publicKeyContent;
    private String privateKeyContent;

    // Getters and setters for Resource keys
    public Resource getPublicKey() { return publicKey; }
    public void setPublicKey(Resource publicKey) { this.publicKey = publicKey; }

    public Resource getPrivateKey() { return privateKey; }
    public void setPrivateKey(Resource privateKey) { this.privateKey = privateKey; }

    // Getters and setters for direct key content strings
    public String getPublicKeyContent() {
        try {
            if (publicKeyContent != null && !publicKeyContent.isBlank()) {
                return publicKeyContent;
            }
            if (publicKey != null && publicKey.exists()) {
                return new String(Files.readAllBytes(publicKey.getFile().toPath()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading public key", e);
        }
        throw new IllegalStateException("No public key configured");
    }

    public void setPublicKeyContent(String publicKeyContent) {
        this.publicKeyContent = publicKeyContent;
    }

    public String getPrivateKeyContent() {
        try {
            if (privateKeyContent != null && !privateKeyContent.isBlank()) {
                return privateKeyContent;
            }
            if (privateKey != null && privateKey.exists()) {
                return new String(Files.readAllBytes(privateKey.getFile().toPath()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading private key", e);
        }
        throw new IllegalStateException("No private key configured");
    }

    public void setPrivateKeyContent(String privateKeyContent) {
        this.privateKeyContent = privateKeyContent;
    }
}
