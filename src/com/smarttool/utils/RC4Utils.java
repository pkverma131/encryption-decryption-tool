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

public class RC4Utils {
	private static final String ALGORITHM = "RC4";
	   public byte[] encryptFile(String file2Encrypt, String key) throws Exception {
		     // create a binary key from the argument key (seed)
		      SecureRandom sr = new SecureRandom(key.getBytes());
		      KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
		      kg.init(sr);
		      SecretKey sk = kg.generateKey();
		  
		      // create an instance of cipher
		      Cipher cipher = Cipher.getInstance(ALGORITHM);
		  
		      // initialize the cipher with the key
		      cipher.init(Cipher.ENCRYPT_MODE, sk);
		  
		      // Encrypt!
		      byte[] encrypted = cipher.doFinal(readFileBytes(file2Encrypt));
		  
		      return encrypted;
	   }
	   public byte[] decryptFile(String file2Decrypt,String key) throws Exception{
		      // create a binary key from the argument key (seed)
		      SecureRandom sr = new SecureRandom(key.getBytes());
		      KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
		      kg.init(sr);
		      SecretKey sk = kg.generateKey();
		  
		      // do the Decryption with that key
		      Cipher cipher = Cipher.getInstance(ALGORITHM);
		      cipher.init(Cipher.DECRYPT_MODE, sk);
		      byte[] decrypted = cipher.doFinal(readFileBytes(file2Decrypt));
		  
		      return decrypted;
	   }
	   public byte[] algoEncrypt(String toEncrypt,String key,String algo)throws Exception{
		      // create a binary key from the argument key (seed)
		      SecureRandom sr = new SecureRandom(key.getBytes());
		      KeyGenerator kg = KeyGenerator.getInstance(algo);
		      kg.init(sr);
		      SecretKey sk = kg.generateKey();
		  
		      // create an instance of cipher
		      Cipher cipher = Cipher.getInstance(algo);
		  
		      // initialize the cipher with the key
		      cipher.init(Cipher.ENCRYPT_MODE, sk);
		  
		      // Encrypt!
		      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
		  
		      return encrypted;
	   }
	   public byte[] encrypt(String toEncrypt, String key) throws Exception {
	      // create a binary key from the argument key (seed)
	      SecureRandom sr = new SecureRandom(key.getBytes());
	      KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
	      kg.init(sr);
	      SecretKey sk = kg.generateKey();
	  
	      // create an instance of cipher
	      Cipher cipher = Cipher.getInstance(ALGORITHM);
	  
	      // initialize the cipher with the key
	      cipher.init(Cipher.ENCRYPT_MODE, sk);
	  
	      // Encrypt!
	      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
	  
	      return encrypted;
	   }
	   public String algoDecrypt(byte[] toDecrypt, String key,String algo) throws Exception {
		      // create a binary key from the argument key (seed)
		      SecureRandom sr = new SecureRandom(key.getBytes());
		      KeyGenerator kg = KeyGenerator.getInstance(algo);
		      kg.init(sr);
		      SecretKey sk = kg.generateKey();
		  
		      // do the Decryption with that key
		      Cipher cipher = Cipher.getInstance(algo);
		      cipher.init(Cipher.DECRYPT_MODE, sk);
		      byte[] decrypted = cipher.doFinal(toDecrypt);
		  
		      return new String(decrypted);
		   }
	   public String decrypt(byte[] toDecrypt, String key) throws Exception {
	      // create a binary key from the argument key (seed)
	      SecureRandom sr = new SecureRandom(key.getBytes());
	      KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
	      kg.init(sr);
	      SecretKey sk = kg.generateKey();
	  
	      // do the Decryption with that key
	      Cipher cipher = Cipher.getInstance(ALGORITHM);
	      cipher.init(Cipher.DECRYPT_MODE, sk);
	      byte[] decrypted = cipher.doFinal(toDecrypt);
	  
	      return new String(decrypted);
	   }
		public byte[] readFileBytes(String inputfile) throws IOException{
		    Path path = Paths.get(inputfile);
		    return Files.readAllBytes(path);        
		}
		public boolean writeBytes2File(String outputfile,byte[] inputbytes){
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
