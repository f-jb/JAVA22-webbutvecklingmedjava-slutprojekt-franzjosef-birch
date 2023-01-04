package accountManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

class FileHandler {
	static boolean doesFileExist(String file) {
		return new File(file).exists();
	}

	static void writeFile(List<Account> objects, String file) {
		try (FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			oos.writeObject(objects);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	static List<Account> readFile(String file) {
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
		
				return (List<Account>) ois.readObject();
			
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
