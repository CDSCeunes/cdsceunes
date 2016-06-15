package br.ufes.cdsceunes.lib.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import br.ufes.cdsceunes.util.security.HashSecurity;


public class HashSecurityTest {

	private String password;
	private String hash;
	
	@Before
	public void constructor() {
		password = "test password";
		hash = HashSecurity.getInstance().generateHash(password);
	}
	
	@Test
	public void testPasswordEqualHash() {
		Assert.assertTrue(HashSecurity.getInstance().checkPassword(password, hash));
	}
	
	@Test
	public void testPasswordDifferentFromHashAssertsFalse() {
		Assert.assertFalse(HashSecurity.getInstance().checkPassword("another password", hash));
	}
	
}
