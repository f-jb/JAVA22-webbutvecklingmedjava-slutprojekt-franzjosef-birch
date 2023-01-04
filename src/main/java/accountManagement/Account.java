package accountManagement;

import java.io.Serializable;

public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String city;
	private String country;
	private String username;

	private Password password;
	private Secret secret;
	
	public String toString() {
		return ("name is " + name);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUsername() {
		return this.username;
	}

	Account(String username, String name, String password, String question, String answer, String city,
			String country) {
		this.username = username;
		this.name = name;
		this.city = city;
		this.country = country;
		this.password = new Password(password);
		this.secret = new Secret(question, answer);
	}

	public Boolean verifyPassword(String password) {
		return this.password.verify(password);
	}

	public void setPassword(String password) {
		this.password = new Password(password);
	}

	public String getSecretQuestion() {
		return secret.getQuestion();
	}

	public Boolean verifySecret(String answer) {
		return this.secret.verify(answer);
	}

	public void setSecret(String question, String answer) {
		this.secret = new Secret(question, answer);
	}

}
