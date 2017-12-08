package br.com.farmacia.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
import org.apache.commons.codec.binary.Base64;

public class StringUtil {

	private static Random random = new Random((new Date()).getTime());

	/**
	 * Encrypt password by using SHA-256 algorithm, encryptedPassword length is
	 * 32 bits
	 * 
	 * @param clearTextPassword
	 * @return
	 * @throws NoSuchAlgorithmException
	 *             reference
	 *             http://java.sun.com/j2se/1.4.2/docs/api/java/security/MessageDigest.html
	 */
	public static String getEncryptedPassword(String clearTextPassword) {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(clearTextPassword.getBytes());
			return Base64.encodeBase64String((md.digest()));
		} catch (NoSuchAlgorithmException e) {
			// _log.error("Failed to encrypt password.", e);
		}
		return "";
	}
}
