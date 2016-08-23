package br.ufes.cdsceunes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.ufes.cdsceunes.util.security.auth.JWTAuthenticationFailureHandler;
import br.ufes.cdsceunes.util.security.auth.JWTAuthenticationSuccessHandler;
import br.ufes.cdsceunes.util.security.auth.StatelessAuthenticationFilter;
import br.ufes.cdsceunes.util.security.auth.TokenAuthenticationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService details;

	public WebSecurityConfig() {
		super(true);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService("CDSCEUNES_SECRET",
				details);
		JWTAuthenticationSuccessHandler authenticationSuccessHandler = new JWTAuthenticationSuccessHandler(
				tokenAuthenticationService);
		JWTAuthenticationFailureHandler authenticationFailureHandler = new JWTAuthenticationFailureHandler();

		http.formLogin().loginProcessingUrl("/login").successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler).and().exceptionHandling().and().anonymous().and()
				.authorizeRequests().antMatchers("/login").permitAll().and()
				.authorizeRequests().antMatchers("/api/**").authenticated().and()
				.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class);
	}

}