package com.sharad.tmanager.config;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

	private static String SALT = "";

	public static void setCurrentSalt(String salt) {
		SALT = salt;
	}

	@Override
	public String encode(CharSequence rawPassword) {

		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {

		System.out.println(encodedPassword+".."+ SALT+".."+ rawPassword.toString());
		return verifyPassword(encodedPassword, SALT, rawPassword.toString());
	}

	public boolean verifyPassword(String storedHash, String storedSalt, String userProvidedPassword) {
		try {
			byte[] salt = Base64.getDecoder().decode(storedSalt);
			char[] password = userProvidedPassword.toCharArray();

			PBEKeySpec spec = new PBEKeySpec(password, salt, 10000, 64 * 8); // Adjust the iterations and key length as
																				// needed
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

			byte[] computedHash = factory.generateSecret(spec).getEncoded();
			System.out.println(computedHash+ "PWDCHECKER:: "+storedHash.equals(Base64.getEncoder().encodeToString(computedHash)));
			return storedHash.equals(Base64.getEncoder().encodeToString(computedHash));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return false;
		}
	}

}