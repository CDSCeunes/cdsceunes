package br.ufes.cdsceunes.util.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufes.cdsceunes.repository.UserDetailsRepository;

@Service("customDetailsService")
public class UsersDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository users;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		br.ufes.cdsceunes.model.UserDetails details = users.findByLogin(username);
		String[] roles = details.getRoles().stream().map(e -> e.getName()).collect(Collectors.toList())
				.toArray(new String[0]);
		return new User(details.getLogin(), details.getPassword(), true, true, true, true,
				AuthorityUtils.createAuthorityList(roles));
	}
	


}
