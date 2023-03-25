package com.dogigiri.core.security.developsecurejavacode.jce.sealedobject;

import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

public class SealedObjectDemo {
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        var employee = new Employee(1L, "David", "Schemer");

        var generator = KeyGenerator.getInstance("AES");
        var secretKey = generator.generateKey();

        var random = new SecureRandom();
        var iv = new byte[16];
        random.nextBytes(iv);

        var cipher = Cipher.getInstance(secretKey.getAlgorithm() + "/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

        var sealedEmployee = new SealedObject(employee, cipher);
        var fileOutputStream = new FileOutputStream("/home/dogigiri/out.aes");
        try (CipherOutputStream cos = new CipherOutputStream(new BufferedOutputStream(fileOutputStream), cipher)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(cos)) {
                oos.writeObject(sealedEmployee);
            }
        }

        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        Employee decryptedSealedEmployee = null;
        try (CipherInputStream cis = new CipherInputStream(
                new BufferedInputStream(new FileInputStream("/home/dogigiri/out.aes")), cipher)) {
            try (ObjectInputStream ois = new ObjectInputStream(cis)) {
                SealedObject so = (SealedObject) ois.readObject();
                decryptedSealedEmployee = (Employee) so.getObject(cipher);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        LoggerFactory.getLogger(SealedObjectDemo.class).info("{}", decryptedSealedEmployee);
    }
}
