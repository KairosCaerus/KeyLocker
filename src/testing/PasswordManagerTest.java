package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import lib.PasswordManager;

class PasswordManagerTest {

	@Test
	void testEncrypt() {
		String[] passwords = {"password", "password123", "1234234234", "$%^&#@$@#$", "some5super2secret0password123", "s2Dgf9d$12df",};
		for(String password : passwords) {
			String encryptedPassword = PasswordManager.encrypt(password);
			assertTrue(password.equals(PasswordManager.decrypt(encryptedPassword)));
		}
	}

}
