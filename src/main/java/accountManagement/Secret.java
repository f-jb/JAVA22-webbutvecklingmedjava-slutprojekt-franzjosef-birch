package accountManagement;

import java.io.Serializable;

class Secret extends Authentication implements Serializable {
	private static final long serialVersionUID = 1L;
	private String question;

	Secret(String question, String answer) {
		this.question = question;
		super.setToken(answer);
	}

	String getQuestion() {
		return this.question;
	}

}
