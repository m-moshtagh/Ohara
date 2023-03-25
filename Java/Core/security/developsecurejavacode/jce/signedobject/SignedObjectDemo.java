package com.dogigiri.core.security.developsecurejavacode.jce.signedobject;

import com.dogigiri.core.security.developsecurejavacode.jce.sealedobject.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;

public class SignedObjectDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignedObjectDemo.class);

    public static void main(String[] args) throws Exception {
        var employee = new Employee(2L, "Peter", "1123");
        var alg = "SHA256withRSA";
        var generator = KeyPairGenerator.getInstance("RSA");
        var keyPair = generator.generateKeyPair();

        SignedObject signedObject = new SignedObject(employee, keyPair.getPrivate(), Signature.getInstance(alg));

        var byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream)) {
            oos.writeObject(signedObject);
        }

        var data = byteArrayOutputStream.toByteArray();
        try (ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            var readSignedObject = (SignedObject) inputStream.readObject();
            boolean result = readSignedObject.verify(keyPair.getPublic(), Signature.getInstance(alg));
            if (result) LOGGER.info("Correct");
            else LOGGER.error("incorrect");
        }
    }
}
