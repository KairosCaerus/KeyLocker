package lib;
import views.GeneratorView;

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
	public static String generate(GeneratorView generatorView) {
		if(generatorView.getIncludeNumber().isSelected()&&generatorView.getSpecialChar().isSelected()) {
			currentString = defaultString+number+specialChar;
		} else if(generatorView.getIncludeNumber().isSelected()&&!(generatorView.getSpecialChar().isSelected())){
			currentString = defaultString+number;
		}else if(!generatorView.getIncludeNumber().isSelected()&&generatorView.getSpecialChar().isSelected()){
			currentString = defaultString+specialChar;
		} else {
			currentString = defaultString;
		}
		int length = Integer.parseInt(generatorView.getPassLength().getText());
		String passOutput = "";
		for(int i = 0; i < length; i++) {
			int randomChar = (int)(Math.random()*currentString.length());
			passOutput = passOutput + currentString.charAt(randomChar);
		}
		return passOutput;
	}
}
