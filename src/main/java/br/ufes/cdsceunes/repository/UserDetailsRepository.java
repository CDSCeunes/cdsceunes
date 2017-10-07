package br.ufes.cdsceunes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufes.cdsceunes.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

	@Query
	public UserDetails findByLogin(@Param("login") String login);
	
	Optional<UserDetails> findOneByLogin(String login);
	
	@Query
	public UserDetails findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

}
