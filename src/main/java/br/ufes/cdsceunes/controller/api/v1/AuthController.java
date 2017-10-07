package br.ufes.cdsceunes.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.cdsceunes.auth.TokenHandler;
import br.ufes.cdsceunes.service.SecurityContextService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenHandler tokenHandler;

	@Autowired
	private SecurityContextService securityContextService;

	@PostMapping()
	public AuthResponse auth(@RequestBody AuthParams params) throws AuthenticationException {
		final UsernamePasswordAuthenticationToken loginToken = params.toAuthenticationToken();
		final Authentication authentication = authenticationManager.authenticate(loginToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return securityContextService.currentUser().map(u -> {
			final String token = tokenHandler.createTokenForUser(u);
			return new AuthResponse(token);
		}).orElseThrow(RuntimeException::new);
	}

	private static final class AuthParams {
		private String username;
		private String password;
		
		public AuthParams() {
			
		}

		public AuthParams(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		UsernamePasswordAuthenticationToken toAuthenticationToken() {
			return new UsernamePasswordAuthenticationToken(username, password);
		}
	}

	private static final class AuthResponse {
		private String token;

		public AuthResponse(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

	}

}
