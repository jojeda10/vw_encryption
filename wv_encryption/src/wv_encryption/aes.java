package wv_encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class aes {
	   //
	   static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile){
		 try {
		       Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
		        Cipher cipher = Cipher.getInstance("AES");
		        // Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		       cipher.init(cipherMode, secretKey);

		       FileInputStream inputStream = new FileInputStream(inputFile);
		       byte[] inputBytes = new byte[(int) inputFile.length()];
		       inputStream.read(inputBytes);

		       byte[] outputBytes = cipher.doFinal(inputBytes);

		       FileOutputStream outputStream = new FileOutputStream(outputFile);
		       outputStream.write(outputBytes);

		       inputStream.close();
		       outputStream.close();

		    } catch (NoSuchPaddingException | NoSuchAlgorithmException 
	                     | InvalidKeyException | BadPaddingException
		             | IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
	            }
	     }
		
	     public static void main(String[] args) {
		String key = "J@NcRfUjWnZr4u7x!A%D*G-KaPdSgVkY";
		//File inputFile = new File("text.txt");
		
		File inputFile = new File("src/AES-256-Implementation-In-Java-master/aes/Representation.pdf");
		
		//File encryptedFile = new File("text.encrypted");
		File encryptedFile = new File("encrypted.pdf");
		File decryptedFile = new File("decrypted-text.pdf");
			
		try {
			 aes.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
			 aes.fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
		     System.out.println("Sucess");
		 } catch (Exception ex) {
		     System.out.println(ex.getMessage());
	             ex.printStackTrace();
		 }
	     }
		
	}