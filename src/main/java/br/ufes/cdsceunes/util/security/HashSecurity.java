package br.ufes.cdsceunes.util.security;

import org.jasypt.util.password.StrongPasswordEncryptor;

public final class HashSecurity {
	
	private static HashSecurity hashSecurity;
	
	private StrongPasswordEncryptor encryptor;
	
	private HashSecurity() {
		encryptor = new StrongPasswordEncryptor();
	}
	
	public static HashSecurity getInstance() {
		if (hashSecurity == null) {
			hashSecurity = new HashSecurity();
		}
		return hashSecurity;
	}
	
	public String generateHash(String password) {
		return encryptor.encryptPassword(password);
	}
	
	public boolean checkPassword(String password, String encrypted) {
		return encryptor.checkPassword(password, encrypted);
	}
}
