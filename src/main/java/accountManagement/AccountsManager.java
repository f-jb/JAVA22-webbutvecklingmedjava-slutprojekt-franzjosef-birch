package accountManagement;

import java.util.ArrayList;
import java.util.List;

public class AccountsManager {
	List<Account> accounts;
	String file = "accounts.dat";

	public AccountsManager() {
		if (FileHandler.doesFileExist(file)) {
			
			this.accounts = (List<Account>) FileHandler.readFile(file);
			
		} else {
			accounts = new ArrayList<Account>();
			saveFile();

		}
	}

	private void saveFile() {
		FileHandler.writeFile(accounts, file);
	}

	public void addAccount(String username, String name, String password, String question, String answer, String city,
			String country) {

		if (userExists(username)) {
			System.out.println("User " + username + " already exists");
		} else {
			accounts.add(new Account(username, name, password, question, answer, city, country));
			saveFile();
		}
	}

	public int getSize() {
		return accounts.size();
	}

	public void deleteAccount(String username) {
		if (userExists(username)) {
			accounts.remove(findIndexOfUser(username));
		} else {
			System.out.println("User " + username + " not found");
		}
	}

	public boolean authenticateUser(String username, String password) {
		if (userExists(username)) {
			
			return accounts.get(findIndexOfUser(username)).verifyPassword(password);
		} else {
			System.out.println("User " + username + " not found");
			return false;
		}
	}

	public Account getAccount(String username) {
		return accounts.get(findIndexOfUser(username));
	}

	public void updateAccount(Account accountToUpdate, String username, String name, String city, String country) {
		accountToUpdate.setName(name);
		accountToUpdate.setCity(city);
		accountToUpdate.setCountry(country);
	}

	public boolean userExists(String username) {
		if (accounts.size() > 0) {
			for (Account account : accounts) {
				if (account.getUsername().equals(username)) {
					return true;
				}
			}
		}
		return false;
	}

	private int findIndexOfUser(String username) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getUsername().equalsIgnoreCase(username)) {
				return i;
			}
		}
		return -1;
	}

}
