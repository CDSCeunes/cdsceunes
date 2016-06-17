package br.ufes.cdsceunes.util.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class HashSecurity {
	
	private static HashSecurity hashSecurity;
	private static final int SALT = 12;
	
	private BCryptPasswordEncoder encoder;
	
	
	private HashSecurity() {
		encoder = new BCryptPasswordEncoder(SALT);
	}
	
	public static HashSecurity getInstance() {
		if (hashSecurity == null) {
			hashSecurity = new HashSecurity();
		}
		return hashSecurity;
	}
	
	public String generateHash(String password) {
		return encoder.encode(password);
	}
	
	public boolean checkPassword(String password, String encrypted) {
		return encoder.matches(password, encrypted);
	}
}
