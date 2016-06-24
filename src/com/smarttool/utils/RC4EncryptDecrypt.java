package com.smarttool.utils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class RC4EncryptDecrypt{
   private static String algorithm = "RC4";
  
   public static byte[] encryptFile(String file2Encrypt, String key) throws Exception {
	     // create a binary key from the argument key (seed)
	      SecureRandom sr = new SecureRandom(key.getBytes());
	      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
	      kg.init(sr);
	      SecretKey sk = kg.generateKey();
	  
	      // create an instance of cipher
	      Cipher cipher = Cipher.getInstance(algorithm);
	  
	      // initialize the cipher with the key
	      cipher.init(Cipher.ENCRYPT_MODE, sk);
	  
	      // Encrypt!
	      byte[] encrypted = cipher.doFinal(readFileBytes(file2Encrypt));
	  
	      return encrypted;
   }
   public static byte[] decryptFile(String file2Decrypt,String key) throws Exception{
	      // create a binary key from the argument key (seed)
	      SecureRandom sr = new SecureRandom(key.getBytes());
	      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
	      kg.init(sr);
	      SecretKey sk = kg.generateKey();
	  
	      // do the Decryption with that key
	      Cipher cipher = Cipher.getInstance(algorithm);
	      cipher.init(Cipher.DECRYPT_MODE, sk);
	      byte[] decrypted = cipher.doFinal(readFileBytes(file2Decrypt));
	  
	      return decrypted;
   }
   public static byte[] encrypt(String toEncrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // create an instance of cipher
      Cipher cipher = Cipher.getInstance(algorithm);
  
      // initialize the cipher with the key
      cipher.init(Cipher.ENCRYPT_MODE, sk);
  
      // Encrypt!
      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
  
      return encrypted;
   }
  
   public static String decrypt(byte[] toDecrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // do the Decryption with that key
      Cipher cipher = Cipher.getInstance(algorithm);
      cipher.init(Cipher.DECRYPT_MODE, sk);
      byte[] decrypted = cipher.doFinal(toDecrypt);
  
      return new String(decrypted);
   }
	public static byte[] readFileBytes(String inputfile) throws IOException{
	    Path path = Paths.get(inputfile);
	    return Files.readAllBytes(path);        
	}
	public static boolean writeBytes2File(String outputfile,byte[] inputbytes){
		boolean written=false;
        try {
             FileOutputStream fos = new FileOutputStream(outputfile);
             fos.write(inputbytes);
             fos.close();
             written=true;
       }
      catch(FileNotFoundException ex)   {
             System.out.println("FileNotFoundException : " + ex);
      }
     catch(IOException ioe)  {
             System.out.println("IOException : " + ioe);
      }
		return written;
	}
}