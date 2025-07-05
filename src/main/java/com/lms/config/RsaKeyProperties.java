
package com.lms.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;

@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {
    private Resource publicKey;
    private Resource privateKey;

    public Resource getPublicKey() { return publicKey; }
    public void setPublicKey(Resource publicKey) { this.publicKey = publicKey; }

    public Resource getPrivateKey() { return privateKey; }
    public void setPrivateKey(Resource privateKey) { this.privateKey = privateKey; }

    public String getPublicKeyContent() throws IOException {
        return new String(Files.readAllBytes(publicKey.getFile().toPath()));
    }

    public String getPrivateKeyContent() throws IOException {
        return new String(Files.readAllBytes(privateKey.getFile().toPath()));
    }
}
