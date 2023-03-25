package com.dogigiri.core.security.developsecurejavacode.jce.encryption.hashing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptDemo {
    private final BCryptPasswordEncoder bcrypt;

    public BcryptDemo(BCryptPasswordEncoder bcrypt) {
        this.bcrypt = bcrypt;
    }

    public String encrypt(String text) {
        return bcrypt.encode(text);
    }

    public boolean verify(String text, String hash) {
        return bcrypt.matches(text, hash);
    }
}
