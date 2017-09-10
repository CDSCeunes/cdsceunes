package br.ufes.cdsceunes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.ufes.cdsceunes.util.security.auth.JWTAuthenticationFailureHandler;
import br.ufes.cdsceunes.util.security.auth.JWTAuthenticationSuccessHandler;
import br.ufes.cdsceunes.util.security.auth.StatelessAuthenticationFilter;
import br.ufes.cdsceunes.util.security.auth.TokenAuthenticationService;

@EnableGlobalMethodSecurity
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userdetails;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetails).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService("CDSCEUNES_SECRET",
				userdetails);
		JWTAuthenticationSuccessHandler authenticationSuccessHandler = new JWTAuthenticationSuccessHandler(
				tokenAuthenticationService);
		JWTAuthenticationFailureHandler authenticationFailureHandler = new JWTAuthenticationFailureHandler();

		http
			.formLogin()
				.loginProcessingUrl("/login")
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and()
			.exceptionHandling()
				.and()
			.anonymous()
				.and()
			.authorizeRequests()
				.antMatchers("/login", "css/**","/dist/**")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/api/**")
				.authenticated()
				.and()
			.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),UsernamePasswordAuthenticationFilter.class);
	}

}