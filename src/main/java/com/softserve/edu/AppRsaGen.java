package com.softserve.edu;

import java.io.File;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class AppRsaGen {
	public static void main(String[] args) throws Exception {
		System.out.println("start");
		/*
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		//
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PrivateKey aPrivate = keyPair.getPrivate();
		PublicKey aPublic = keyPair.getPublic();
		//
		try (FileOutputStream outPrivate = new FileOutputStream("key.pri")) {
			outPrivate.write(aPrivate.getEncoded());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try (FileOutputStream outPublic = new FileOutputStream("key.pub")) {
			outPublic.write(aPublic.getEncoded());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		*/
		//
		File privateKeyFile = new File("key.pri");
		byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
		//
		KeyFactory privateKeyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey privateKey = privateKeyFactory.generatePrivate(privateKeySpec);
		System.out.println("\nprivateKey = " + privateKey);
		//
		File publicKeyFile = new File("key.pub");
		byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
		//
		KeyFactory publicKeyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey publicKey = publicKeyFactory.generatePublic(publicKeySpec);
		System.out.println("\npublicKey = " + publicKey);

		System.out.println("done");
	}
}
