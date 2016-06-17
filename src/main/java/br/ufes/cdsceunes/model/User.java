package br.ufes.cdsceunes.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.NotBlank;

import br.ufes.cdsceunes.util.security.HashSecurity;

@MappedSuperclass
public abstract class User extends AbstractModel {
	
	@NotBlank
	@Column(length = 50)
	protected String login;
	
	protected String password;
	
	@NotBlank
	protected Role role;
	
	abstract void setRole(Role role);

}
