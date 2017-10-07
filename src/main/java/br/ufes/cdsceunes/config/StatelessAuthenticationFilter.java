package br.ufes.cdsceunes.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import br.ufes.cdsceunes.auth.TokenAuthenticationService;
import io.jsonwebtoken.JwtException;

@Component
public class StatelessAuthenticationFilter extends GenericFilterBean {

	private final TokenAuthenticationService tokenAuthService;

    public StatelessAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthService = tokenAuthenticationService;
    }

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {
			Authentication authentication = tokenAuthService.getAuthentication((HttpServletRequest) req);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(req, res);
			SecurityContextHolder.getContext().setAuthentication(null);
		} catch (AuthenticationException | JwtException e) {
			SecurityContextHolder.clearContext();
			((HttpServletResponse) res).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

}
