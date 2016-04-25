package sbs.src.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "authorities")
public class Authorities {

	@Id
	@NotBlank(message="Username cannot be blank.")
	@Column(name = "username")
	private String username;

	@NotBlank(message="Password can not be blank.")
	@Column(name = "authority")
	private String authority;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Authorities(){
		
	}
	
	public Authorities(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authorities [username=" + username + ", authority=" + authority
				+ "]";
	}
	

}
