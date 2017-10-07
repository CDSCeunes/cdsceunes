package br.ufes.cdsceunes.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.cdsceunes.model.UserDetails;
import br.ufes.cdsceunes.repository.UserDetailsRepository;

@Service
@Transactional
public class SecurityContextServiceImpl implements SecurityContextService {

	@Autowired
	private UserDetailsRepository repository;
	
	@Override
	public Optional<UserDetails> currentUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return repository.findOneByLogin(authentication.getName());
	}

}
