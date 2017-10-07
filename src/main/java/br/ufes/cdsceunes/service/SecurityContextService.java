package br.ufes.cdsceunes.service;

import java.util.Optional;

import br.ufes.cdsceunes.model.UserDetails;

public interface SecurityContextService {

	Optional<UserDetails> currentUser();
}
