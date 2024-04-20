package com.example.client.encrypt;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncryptionUtil {
  private String key = "1234567812345678";
  private String vector = "1234567812345678";
  private String algorithm = "AES/CBC/PKCS5PADDING";

  public String encryptFile(File file){
    try {
      IvParameterSpec iv = new IvParameterSpec(vector.getBytes(StandardCharsets.UTF_8));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
      
      Cipher cipher = Cipher.getInstance(algorithm);
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
      
      byte[] encrypted = cipher.doFinal(Files.readAllBytes(file.toPath()));
      return Base64.encodeBase64String(encrypted);
    } catch (Exception error) { 
      error.printStackTrace();
      return null;
    }
  }

  public String encryptString(String string){
    try {
      IvParameterSpec iv = new IvParameterSpec(vector.getBytes(StandardCharsets.UTF_8));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
      
      Cipher cipher = Cipher.getInstance(algorithm);
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
      
      byte[] encrypted = cipher.doFinal(string.getBytes());
      return Base64.encodeBase64String(encrypted);
    } catch (Exception error) { 
      error.printStackTrace();
      return null;
    }
  }

  public String decrypt(String encrypted) {
    try {
      IvParameterSpec iv = new IvParameterSpec(vector.getBytes(StandardCharsets.UTF_8));
      SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
    
      Cipher cipher = Cipher.getInstance(algorithm);
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    
      byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
      return new String(original);
    } catch (Exception error) {
      System.err.println(error.getMessage());
      error.printStackTrace();
      return null;
    }
  }
}