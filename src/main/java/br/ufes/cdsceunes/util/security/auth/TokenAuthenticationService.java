package br.ufes.cdsceunes.util.security.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public class TokenAuthenticationService {
	
	private UserDetailsService details;
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

	private final TokenHandler tokenHandler;

	public TokenAuthenticationService(String secret, UserDetailsService details) {
		this.details = details;
		tokenHandler = new TokenHandler(secret, this.details);
	}

	public void addAuthentication(HttpServletResponse response, Authentication authentication) {
		final User user = (User) authentication.getPrincipal();
		response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
