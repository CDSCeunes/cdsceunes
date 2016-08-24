package br.ufes.cdsceunes.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufes.cdsceunes.repository.UserDetailsRepository;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private UserDetailsRepository users;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(provider);
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				br.ufes.cdsceunes.model.UserDetails details = users.findByLogin(username);				
				String[] roles = details.getRoles().stream().map(e -> e.getName()).collect(Collectors.toList()).toArray(new String[0]);
				return new User(details.getLogin(), details.getPassword(), true, true, true, true,
						AuthorityUtils.createAuthorityList(roles));
			}
		};
	}
}
