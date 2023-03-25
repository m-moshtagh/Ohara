package com.dogigiri.core.security.developsecurejavacode.jce.encryption.hashing;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class SCryptDemo {
    private final SCryptPasswordEncoder encoder;

    public SCryptDemo(SCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encrypt(String text) {
        return encoder.encode(text);
    }

    public boolean verify(String text, String hash) {
        return encoder.matches(text, hash);
    }
}
