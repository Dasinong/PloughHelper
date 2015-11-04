package com.dasinong.ploughHelper.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author xiahonggao
 *
 * AES 双向加密算法，可用来生成UserAccessToken或者AppAccessTOken
 */
public class AES {

	public static final String encrypt(final String message, final String secret) throws Exception {
      Cipher cipher = Cipher.getInstance("AES");
      SecretKeySpec spec = new SecretKeySpec(Base64.decodeBase64(secret), "AES");
      cipher.init(Cipher.ENCRYPT_MODE, spec);
      byte[] stringBytes = message.getBytes();
      byte[] raw = cipher.doFinal(stringBytes);
      return Base64.encodeBase64String(raw);
	}

	public static final String decrypt(final String encrypted,final String secret) throws Exception {
      Cipher cipher = Cipher.getInstance("AES");
      SecretKeySpec spec = new SecretKeySpec(Base64.decodeBase64(secret), "AES");
      cipher.init(Cipher.DECRYPT_MODE, spec);
      
      byte[] raw = Base64.decodeBase64(encrypted);
      byte[] stringBytes = cipher.doFinal(raw);
      String clearText = new String(stringBytes, "UTF8");
      return clearText;
	}

	public static void main(String[] args) throws Exception{
      String key = Base64.encodeBase64String("woShiZhaoRiTian!".getBytes());

      String clearText = "12345,2,1445966485";
      System.out.println("Clear Text:" + clearText);
      String encryptedString = AES.encrypt(clearText, key);
      System.out.println("Encrypted String:" + encryptedString);
      String decryptedString = AES.decrypt(encryptedString, key);
      System.out.println("Decrypted String:"+ decryptedString);
   }

}