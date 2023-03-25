package com.dogigiri.core.security.developsecurejavacode.jce.cipherstreams;

import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.io.*;
import java.security.SecureRandom;

public class CipherStreamDemo {
    public static void main(String[] args) throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256);
        SecretKey secretKey = generator.generateKey();

        byte[] iv = new byte[12];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(16 * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        File file = new File("LICENSE");

        byte[] data;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            data = inputStream.readAllBytes();
        }

        try (CipherOutputStream outputStream = new CipherOutputStream(
                new FileOutputStream("/home/dogigiri/csd.out"), cipher)) {
            outputStream.write(data);
        }

        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
        String content;
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new CipherInputStream(new FileInputStream("/home/dogigiri/csd.out"), cipher)))) {
            content = bufferedReader.readLine();
        }

        LoggerFactory.getLogger(CipherStreamDemo.class).info("{}", content);
    }
}
