package br.ufes.cdsceunes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@EnableGlobalMethodSecurity
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userService;

	private final StatelessAuthenticationFilter statelessAuthenticationFilter;

	private final BCryptPasswordEncoder passwordEncoder;

	
	@Autowired
    public SecurityConfig(UserDetailsService userService, StatelessAuthenticationFilter statelessAuthenticationFilter, BCryptPasswordEncoder passwordEncoder) {
        super(true);
        this.userService = userService;
        this.statelessAuthenticationFilter = statelessAuthenticationFilter;
        this.passwordEncoder = passwordEncoder;
    }

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetails).passwordEncoder(encoder);
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService("CDSCEUNES_SECRET",
				detailsService);
		JWTAuthenticationSuccessHandler authenticationSuccessHandler = new JWTAuthenticationSuccessHandler(
				tokenAuthenticationService);
		JWTAuthenticationFailureHandler authenticationFailureHandler = new JWTAuthenticationFailureHandler();*/

		http
			.exceptionHandling()
			.authenticationEntryPoint(new Http401AuthenticationEntryPoint("'Bearer token_type=\"JWT\"'"))
				.and()
			.anonymous()
				.and()
			.authorizeRequests()
				.antMatchers("/login", "css/**","/dist/**", "/api/v1/auth")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/api/**")
				.authenticated()
				.and()
			.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
				.addFilterBefore(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	public FilterRegistrationBean registration(StatelessAuthenticationFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userService;
	}

}