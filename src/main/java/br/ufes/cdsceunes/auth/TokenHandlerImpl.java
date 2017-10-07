package br.ufes.cdsceunes.auth;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.ufes.cdsceunes.repository.UserDetailsRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHandlerImpl implements TokenHandler {

	private final String secret;

	private final UserDetailsRepository repository;

	@Autowired
	public TokenHandlerImpl(@Value("${app.jwt.secret}") String secret, UserDetailsRepository repository) {
		this.secret = secret;
		this.repository = repository;
	}

	@Override
	public Optional<UserDetails> parseUserFromToken(String token) {
		final String subject = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		final UserDetails user = repository.findOne(Long.valueOf(subject));
		return Optional.ofNullable(user);
	}

	@Override
	public String createTokenForUser(br.ufes.cdsceunes.model.UserDetails user) {
		final ZonedDateTime afterOneWeek = ZonedDateTime.now().plusWeeks(1);
		
		return Jwts.builder()
				.setSubject(user.getId().toString())
				.signWith(SignatureAlgorithm.HS512, secret)
				.setExpiration(Date.from(afterOneWeek.toInstant()))
				.compact();
	}

}
