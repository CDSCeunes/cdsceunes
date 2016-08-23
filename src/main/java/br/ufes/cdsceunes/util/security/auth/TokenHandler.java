package br.ufes.cdsceunes.util.security.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {
	private final String secret;
	private final UserDetailsService details;

	public TokenHandler(String secret, UserDetailsService details) {
		this.secret = secret;
		this.details = details;
	}

	public User parseUserFromToken(String token) {
		String username = (String) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("username");
		return (User) details.loadUserByUsername(username);
	}

	public String createTokenForUser(User user) {

		final long iat = System.currentTimeMillis() / 1000l;
		final long exp = iat + (1L * 10L);
		Map<String, Object> claims = new HashMap<>();
		claims.put("iat", iat);
		claims.put("exp", exp);
		claims.put("username", user.getUsername());
		
		claims.put("roles", user.getAuthorities());
		user.getAuthorities().stream().forEach(System.out::print);
		
		String jwt = Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
		return jwt;
	}
}
