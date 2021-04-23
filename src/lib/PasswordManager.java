package lib;

public class PasswordManager {
	
	/**
	 * Encrypts the passed String
	 * 
	 * @param passwordToEncrypt string to be encrypted
	 * @return encrypted string
	 */
	public static String encrypt(String passwordToEncrypt) {
		char[] text = passwordToEncrypt.toCharArray();
		
		text[0] = (char)(((int)text[0] + 100) % 256);
		
		for(int i = 1; i < text.length; i++) {
			int asciiVal = text[i];
			int asciiValPrev = text[i-1];
			text[i] = (char)((asciiVal + 100 + asciiValPrev) % 256);
		}
		
		return new String(text);
	}

	/**
	 * Decrypts the passed String
	 * 
	 * @precondition Password passed should be encrypted using PasswordManager
	 * @param passwordToDecrypt encrypted password
	 * @return decrypted string
	 */
	public static String decrypt(String passwordToDecrypt) {
		char[] text = passwordToDecrypt.toCharArray();
		
		for(int i = text.length - 1; i > 0; i--) {
			int asciiVal = text[i];
			int asciiValPrev = text[i-1];
			text[i] = asciiVal - 100 - asciiValPrev >= 0 ? (char)(asciiVal - 100 - asciiValPrev): (char)(asciiVal - 100 - asciiValPrev + 256);
		}
		text[0] = (int)text[0] - 100 >= 0 ? (char)((int)text[0] - 100): (char)((int)text[0] - 100 + 256);
		
		return new String(text);
	}
}
