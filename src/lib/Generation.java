package lib;

public class Generation {
	
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
}
