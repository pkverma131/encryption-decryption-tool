package com.smarttool.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class RC4WithASCII {
	public String encrypt ( String clearText, String key ) {
		byte[] cipherText;
		try {
			Cipher rc4 = Cipher.getInstance("RC4");
			SecretKeySpec rc4Key = new SecretKeySpec(key.getBytes("ASCII"), "RC4");
			rc4.init(Cipher.ENCRYPT_MODE, rc4Key);
			cipherText = rc4.update(clearText.getBytes("ASCII"));
			return Base64.encodeBase64String(cipherText);
		} 
		catch (Exception e) { 
			return null; 
		}
	}
		
	public String decrypt (String ciphertextString, String key ) {
		byte[] clearText;
		byte[] cipherText = new byte[Base64.decodeBase64(ciphertextString).length];
		try {
			int counter = 0;
			while (counter < Base64.decodeBase64(ciphertextString).length) {
				cipherText[counter] = (byte)Base64.decodeBase64(ciphertextString)[counter];
				counter++;
				}
			Cipher rc4 = Cipher.getInstance("RC4");
			SecretKeySpec rc4Key = new SecretKeySpec(key.getBytes("ASCII"), "RC4");
			rc4.init(Cipher.DECRYPT_MODE, rc4Key);
			clearText = rc4.update(cipherText);
			return new String(clearText, "ASCII");
		} 
		catch (Exception e) { 
			return ""; 
		}			
	}
  
  }