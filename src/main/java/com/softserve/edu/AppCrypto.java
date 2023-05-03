package com.softserve.edu;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
//import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Array;
import java.security.*;
import java.util.Arrays;

public class AppCrypto {

    public static void main(String[] args) throws Exception {
        /*
        Provider[] providers = Security.getProviders();
        for (Provider p : providers) {
            System.out.println(p.getName());
        }
        */
        /*
        MessageDigest digester = MessageDigest.getInstance("SHA-512");
        byte[] input = "Secret string".getBytes();
        byte[] digest  = digester.digest(input);
        //
        byte[] salt = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(salt);
        digester.update(salt);
        //
        System.out.println("Lenght = " + digest.length + "  " + Arrays.toString(digest));
        System.out.println("salt = " + salt.length + "  " + Arrays.toString(salt));
        System.out.println(DatatypeConverter.printHexBinary(digest));
        */
        /*
        String text = "secret!!secret!!secret!!secret!!";
        // Generate new key
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //keygen.init(256);
        keygen.init(128);
        Key key = keygen.generateKey();
        // Encrypt with key
        String transformation = "AES/ECB/PKCS5Padding";
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        //
        System.out.println(DatatypeConverter.printHexBinary(encrypted));
        // Decrypt with key
        cipher.init(Cipher.DECRYPT_MODE, key);
        String result = new String(cipher.doFinal(encrypted));
        System.out.println(result);
        */
        /*
        // Initialization Vector
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] rnd = new byte[16];
        random.nextBytes(rnd);
        IvParameterSpec ivSpec = new IvParameterSpec(rnd);
        System.out.println("ivSpec: " + Arrays.toString(rnd));
        // Prepare key
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256);
        Key key = keygen.generateKey();
        // CBC
        String text = "secret!!secret!!secret!!secret!!";
        String transformation = "AES/CBC/PKCS5Padding";
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] enc = cipher.doFinal(text.getBytes());
        System.out.println(DatatypeConverter.printHexBinary(enc));
        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        String result = new String(cipher.doFinal(enc));
        System.out.println(result);
        */
        /*
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        //generator.initialize(1024);
        generator.initialize(512);
        KeyPair keyPair = generator.generateKeyPair();
        // Encrypt with PRIVATE KEY
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] data = cipher.doFinal("Hello!".getBytes());
        //
        ///System.out.println(DatatypeConverter.printHexBinary(data));
        // Decrypt with PUBLIC KEY
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] result = cipher.doFinal(data);
        System.out.println(new String(result));
        */
        //
        // Generate keys
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = SecureRandom.getInstanceStrong();
        generator.initialize(2048, random);
        KeyPair keyPair = generator.generateKeyPair();
        // Digital Signature
        Signature dsa = Signature.getInstance("SHA256withRSA");
        dsa.initSign(keyPair.getPrivate());
        // Update and sign the data
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] data = cipher.doFinal("Hello!".getBytes());
        System.out.println("        data: " + Arrays.toString(data));
        dsa.update(data);
        System.out.println("update(data): " + Arrays.toString(data));
        byte[] signature = dsa.sign();
        System.out.println("signature size = " + signature.length + "  sign = " + Arrays.toString(signature));
        //
        // Verify signature
        dsa.initVerify(keyPair.getPublic());
        dsa.update(data);
        boolean verifies = dsa.verify(signature);
        System.out.println("Signature is ok: " + verifies);
        // Decrypt if signature is correct
        if (verifies) {
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            byte[] result = cipher.doFinal(data);
            System.out.println(new String(result));
        }
        //

    }

}
