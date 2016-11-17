package br.ufes.cdsceunes.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_details")
public class UserDetails extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(length = 50, unique = true)
	private String login;

	@JsonIgnore
	private String password;

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles;

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setRole(Role role) {
		roles.add(role);
	}

}
