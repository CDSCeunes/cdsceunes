package br.ufes.cdsceunes.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.cdsceunes.repository.UserDetailsRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private final SecurityContextService securityContextService;
    private final UserDetailsRepository userRepository;

    @Autowired
    public UserServiceImpl(UserDetailsRepository userRepository,
                           SecurityContextService securityContextService) {
        this.userRepository = userRepository;
        this.securityContextService = securityContextService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<br.ufes.cdsceunes.model.UserDetails> user = userRepository.findOneByLogin(username);
        final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        user.ifPresent(detailsChecker::check);
        return user.orElseThrow(() -> new UsernameNotFoundException("user not found."));
    }

}
