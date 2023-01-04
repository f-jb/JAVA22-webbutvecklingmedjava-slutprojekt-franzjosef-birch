package accountManagement;

import java.io.Serializable;
import java.nio.charset.Charset;

abstract class Authentication implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte[] token;
	private byte[] salt;
	
	void setToken(String string) {
		this.salt = Hashing.generateSalt();
		this.token = Hashing.createHash(stringToByteArray(string), this.salt);
	}

	boolean verify(String providedToken) {
		return Hashing.verifyToken(Hashing.createHash(stringToByteArray(providedToken), salt), this.token);
	}


	byte[] stringToByteArray(String string) {
		return string.getBytes(Charset.forName("UTF-8"));
	}

}
