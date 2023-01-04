package accountManagement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

class Hashing {

	static byte[] createHash(byte[] token, byte[] salt) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(token);
			messageDigest.update(salt);
			return messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	static byte[] generateSalt() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[8];
		secureRandom.nextBytes(salt);
		return salt;
	}

	static boolean verifyToken(byte[] providedToken, byte[] storedToken) {
		return MessageDigest.isEqual(providedToken, storedToken);
	}

}
