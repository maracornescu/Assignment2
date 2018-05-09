package business;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

public class RandomToken {
	
	public static String generateToken() {
	    SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[128];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    String token = encoder.encodeToString(bytes);
		return token;
	}
	
}
