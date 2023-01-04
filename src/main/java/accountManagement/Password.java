package accountManagement;

import java.io.Serializable;

class Password extends Authentication implements Serializable{

	private static final long serialVersionUID = 1L;

	Password(String password) {
	super.setToken(password);
	}
	

}
