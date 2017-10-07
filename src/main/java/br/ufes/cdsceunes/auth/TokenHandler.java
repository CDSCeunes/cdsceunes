package br.ufes.cdsceunes.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public interface TokenHandler {

	Optional<UserDetails> parseUserFromToken(String token);

	String createTokenForUser(br.ufes.cdsceunes.model.UserDetails user);
}
