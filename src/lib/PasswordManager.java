package lib;

public class PasswordManager {
	
	private static final String defaultString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String number = "1234567890";
	private static final String specialChar = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	private static String currentString="";
	
	/**
	 * Generates a password based on the user's needs
	 * 
	 * @return passOutput Randomly generated password
	 */
	public static String generate(int length, boolean includeNumber, boolean includeSpecialChar) {
		if(includeNumber && includeSpecialChar) {
			currentString = defaultString+number+specialChar;
		} else if(includeNumber && !includeSpecialChar){
			currentString = defaultString+number;
		}else if(!includeNumber && includeSpecialChar){
			currentString = defaultString+specialChar;
		} else {
			currentString = defaultString;
		}
		String passOutput = "";
		for(int i = 0; i < length; i++) {
			int randomChar = (int)(Math.random()*currentString.length());
			passOutput = passOutput + currentString.charAt(randomChar);
		}
		return passOutput;
	}
	
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
